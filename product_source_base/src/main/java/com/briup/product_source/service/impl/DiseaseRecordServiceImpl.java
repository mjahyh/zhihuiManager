package com.briup.product_source.service.impl;

import com.briup.product_source.dao.DiseaseRecordMapper;
import com.briup.product_source.dao.ManagerAnimalMapper;
import com.briup.product_source.dao.ManagerDiseaseMapper;
import com.briup.product_source.dao.ext.DiseaseRecordExtMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.DiseaseRecord;
import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ManagerDisease;
import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.DiseaseRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hlmove
 */
@Service
@RequiredArgsConstructor
public class DiseaseRecordServiceImpl implements DiseaseRecordService {
    private final ManagerDiseaseMapper managerDiseaseMapper;
    private final DiseaseRecordExtMapper diseaseRecordExtMapper;
    private final DiseaseRecordMapper diseaseRecordMapper;
    private final ManagerAnimalMapper managerAnimalMapper;


    @Override
    public List<ManagerDisease> queryAllDisease() {
        return managerDiseaseMapper.findAllDisease();
    }

    @Override
    public PageInfo<DiseaseRecordExt> queryByConditionsAndPage(Integer pageNum, Integer pageSize, String drStatus, String drDId) {
        PageHelper.startPage(pageNum, pageSize);
        List<DiseaseRecordExt> list = diseaseRecordExtMapper.findByConditionsAndPage(drStatus, drDId);
        return new PageInfo<>(list);
    }

    @Override
    public void saveOrUpdate(DiseaseRecord record) {
        String drAnimalId = record.getDrAnimalId();
        // 参数校验
        if (!StringUtils.hasText(drAnimalId) ||
                !StringUtils.hasText(record.getDrDesc()) ||
                record.getDrDId() == null ||
                record.getDrTime() == null) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }

        // 根据动物编号查询动物信息，只有养殖中的才能添加病症记录
        ManagerAnimal managerAnimal = managerAnimalMapper.selectByPrimaryKey(drAnimalId);
        if (managerAnimal == null) {
            throw new ServiceException(ResultCode.FAIL);
        } else if (!"养殖中".equals(managerAnimal.getAStatus())) {
            throw new ServiceException(ResultCode.ANIMAL_IS_NOT_IN_BREEDING);
        }

        // 添加或者修改诊疗记录
        int result; // sql语句执行后，返回受影响的行数result
        Integer drId = record.getDrId();
        String drStatus = record.getDrStatus();
        if (drId != null) {
            // 有id->更新操作
            if (diseaseRecordMapper.selectByPrimaryKey(drId) != null) {
                result = diseaseRecordMapper.updateByPrimaryKey(record);
            } else {
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
        } else {
            // 无id->新增操作
            // 未传诊疗状态，默认为未治疗
            if (!StringUtils.hasText(drStatus)) {
                record.setDrStatus("未治疗");
            }
            result = diseaseRecordMapper.insert(record);
        }

        // 添加病症记录失败
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }

        // 添加病症记录成功，根据诊疗状态修改动物健康状态
        String aHealthy = "健康";
        if (!"已治疗".equals(drStatus)) {
            aHealthy = "生病";
        }
        if (managerAnimalMapper.updateAHealthyByAAnimalId(aHealthy, drAnimalId) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void deleteById(Integer drId) {
        DiseaseRecord diseaseRecord = diseaseRecordMapper.selectByPrimaryKey(drId);
        if (diseaseRecord != null) {
            int result = diseaseRecordMapper.deleteByPrimaryKey(drId);
            if (result == 0) {
                throw new ServiceException(ResultCode.FAIL);
            }
        } else {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
    }

    @Override
    public void deleteBatch(List<Integer> drIds) {
        List<Integer> deleteIds = new ArrayList<>();
        for (Integer drId : drIds) {
            DiseaseRecord diseaseRecord = diseaseRecordMapper.selectByPrimaryKey(drId);
            if (diseaseRecord == null) {
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            } else {
                deleteIds.add(drId);
            }
        }
        if (deleteIds.size() == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
        int deleteResult = diseaseRecordMapper.deleteBatch(deleteIds);
        if (deleteResult == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }
}
