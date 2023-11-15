package com.briup.product_source.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.briup.product_source.constant.Constants;
import com.briup.product_source.dao.ManagerAnimalMapper;
import com.briup.product_source.dao.ManagerBatchMapper;
import com.briup.product_source.dao.ManagerHurdlesMapper;
import com.briup.product_source.dao.ManagerQrcodeMapper;
import com.briup.product_source.dao.ext.ManagerAnimalExtMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ManagerBatch;
import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ManagerQrcode;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.ManagerAnimalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Hlmove
 */
@Service
@RequiredArgsConstructor
public class ManagerAnimalServiceImpl implements ManagerAnimalService {
    private final ManagerAnimalExtMapper managerAnimalExtMapper;
    private final ManagerAnimalMapper managerAnimalMapper;
    private final ManagerBatchMapper managerBatchMapper;
    private final ManagerHurdlesMapper managerHurdlesMapper;
    private final ManagerQrcodeMapper managerQrcodeMapper;

    @Override
    public PageInfo<ManagerAnimalExt> queryByConditionsAndPage(Integer pageNum, Integer pageSize, String aHealthy, String aGender) {
        PageHelper.startPage(pageNum, pageSize);
        List<ManagerAnimalExt> list = managerAnimalExtMapper.findByPage(aHealthy, aGender);
        return new PageInfo<>(list);
    }

    @Override
    public void saveOrUpdate(ManagerAnimal animal) {
        String aBatchId = animal.getABatchId(); // 批次编号
        String aHurdlesIdNew = animal.getAHurdlesId(); // 栏圈编号
        // 根据栏圈编号查询栏圈信息
        ManagerHurdles managerHurdlesNew = managerHurdlesMapper.selectByPrimaryKey(aHurdlesIdNew);
        if (managerHurdlesNew == null) {
            throw new ServiceException(ResultCode.FAIL);
        }
        Integer hSavedNew = managerHurdlesNew.getHSaved(); // 栏圈已存猪的数量
        Integer hMaxNew = managerHurdlesNew.getHMax(); // 栏圈最大容量
        // 添加或者修改动物信息
        String aAnimalId = animal.getAAnimalId();
        if (StringUtils.hasText(aAnimalId)) {
            // 有id->更新操作
            // 根据id查询动物信息
            ManagerAnimal managerAnimal = managerAnimalMapper.selectByPrimaryKey(aAnimalId);
            if (managerAnimal == null) {
                // 动物不存在
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
            // 动物存在，修改动物信息
            int result = managerAnimalMapper.updateByPrimaryKey(animal);
            if (result == 0) {
                // 修改失败
                throw new ServiceException(ResultCode.FAIL);
            }
            // 修改成功，判断是否修改了栏圈信息
            String aHurdlesIdOld = managerAnimal.getAHurdlesId();
            ManagerHurdles managerHurdlesOld = managerHurdlesMapper.selectByPrimaryKey(aHurdlesIdOld);
            if (!aHurdlesIdNew.equals(aHurdlesIdOld)) {
                // 修改新的栏圈信息
                managerHurdlesNew.setHSaved(hSavedNew + 1);
                if (hSavedNew + result == hMaxNew) {
                    managerHurdlesNew.setHFull("已满");
                }
                if (managerHurdlesMapper.updateByPrimaryKey(managerHurdlesNew) == 0) {
                    throw new ServiceException(ResultCode.FAIL);
                }

                // 修改老的栏圈信息
                managerHurdlesOld.setHSaved(managerHurdlesOld.getHSaved() - 1);
                if ("已满".equals(managerHurdlesOld.getHFull())) {
                    managerHurdlesOld.setHFull("未满");
                }
                if (managerHurdlesMapper.updateByPrimaryKey(managerHurdlesOld) == 0) {
                    throw new ServiceException(ResultCode.FAIL);
                }
            }
        } else {
            // 无id->新增操作
            // 根据批次编号查询批次信息
            ManagerBatch managerBatch = managerBatchMapper.selectByPrimaryKey(aBatchId);
            if (managerBatch == null) {
                throw new ServiceException(ResultCode.FAIL);
            }
            // 批次存在，根据批次检疫状态，动态设置动物过程状态
            if ("已检疫".equals(managerBatch.getBQuarantine())) {
                animal.setAStatus("已检疫");
            } else {
                animal.setAStatus("养殖中");
            }
            // 执行添加
            animal.setAAnimalId(UUID.randomUUID().toString().replace("-", ""));
            int result = managerAnimalMapper.insert(animal);
            if (result == 0) {
                // 添加失败
                throw new ServiceException(ResultCode.FAIL);
            }
            // 添加成功，修改栏圈信息
            managerHurdlesNew.setHSaved(hSavedNew + 1);
            if (hSavedNew + result == hMaxNew) {
                // 栏圈已满，修改h_Full字段
                managerHurdlesNew.setHFull("已满");
            }
            if (managerHurdlesMapper.updateByPrimaryKey(managerHurdlesNew) == 0) {
                throw new ServiceException(ResultCode.FAIL);
            }
        }
    }

    @Override
    public void deleteById(String aAnimalId) {
        ManagerAnimal managerAnimal = managerAnimalMapper.selectByPrimaryKey(aAnimalId);
        if (managerAnimal != null) {
            managerAnimalMapper.deleteById(aAnimalId);
        } else {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
    }

    @Override
    public void deleteByIdAll(List<String> aAnimalIds) {
        List<String> deleteIds = aAnimalIds.stream().filter(aAnimalId -> !StringUtils.isEmpty(managerAnimalMapper.selectByPrimaryKey(aAnimalId)))
                .collect(Collectors.toList());
        int deleteResult = managerAnimalMapper.deleteBatch(deleteIds);
        if (deleteResult == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void createQRcodeByAnimalId(String id) {
        // 通过UUID命名
        String fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";

        // 将文件保存到磁盘
        File QRFile = new File(Constants.FILE_LOCATION, fileName);

        // 二维码图片地址
        String qrUrl = Constants.SERVER.concat(fileName);
        if (managerAnimalMapper.selectByPrimaryKey(id) != null) {
            // 生成指定url对应的二维码到文件，宽和高都是300像素
            String qrCodeUrl = Constants.QRcodeServer.concat(Constants.Prefix).concat(id);
            QrCodeUtil.generate(qrCodeUrl, 300, 300, QRFile);
        } else {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
        // 创建二维码对象设值并存入数据库
        ManagerQrcode qrcode = new ManagerQrcode();
        qrcode.setQAnimalId(id);
        qrcode.setQImageUrl(qrUrl);
        ManagerQrcode mq = managerQrcodeMapper.getECodeByAnimalId(id);
        if (mq == null) {
            managerQrcodeMapper.insert(qrcode);
        } else {
            qrcode.setQId(mq.getQId());
            managerQrcodeMapper.updateByPrimaryKey(qrcode);
        }
    }

    @Override
    public ManagerAnimalExt findById(String id) {
        return managerAnimalExtMapper.findById(id);
    }
}
