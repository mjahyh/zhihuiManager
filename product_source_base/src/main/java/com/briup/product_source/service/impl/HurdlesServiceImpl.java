package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerHurdlesMapper;
import com.briup.product_source.dao.ext.ManagerHurdlesExtMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.HurdlesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Hlmove
 */
@Service
@RequiredArgsConstructor
public class HurdlesServiceImpl implements HurdlesService {
    private final ManagerHurdlesMapper managerHurdlesMapper;

    private final ManagerHurdlesExtMapper managerHurdlesExtMapper;


    @Override
    public List<Integer> findAllMax() {
        return managerHurdlesMapper.findAllMax();
    }

    @Override
    public PageInfo<ManagerHurdlesExt> findByPage(Integer pageNum, Integer pageSize,
                                                  String hName, Integer hMax, String fhName, String hEnable) {
        PageHelper.startPage(pageNum, pageSize);
        List<ManagerHurdlesExt> list = managerHurdlesExtMapper.findByPage(hName, hMax, fhName, hEnable);
        return new PageInfo<>(list);
    }

    @Override
    public void changeStatus(String hId, String hEnable) {
        String status = "可用";
        if (managerHurdlesMapper.selectByPrimaryKey(hId) != null) {
            if (status.equals(hEnable)) {
                status = "禁用";
            }
            int result = managerHurdlesMapper.changeHEnable(hId, status);
            if (result == 0) {
                throw new ServiceException(ResultCode.FAIL);
            }
        } else {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
    }

    @Override
    public void modifyStatusBatch(List<Map<String, String>> hidAndStatus) {
        //统计更新条数，若0则说明没有更新
        int result = 0;
        for (Map<String, String> idAndStatus : hidAndStatus) {
            String hId = idAndStatus.get("hId");
            if (managerHurdlesMapper.selectByPrimaryKey(hId) != null) {
                String hEnable = idAndStatus.get("hEnable");
                //调用单个关闭的方法
                changeStatus(hId, hEnable);
                result++;
            }
        }
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void saveOrUpdate(ManagerHurdles managerHurdles) {
        String hName = managerHurdles.getHName();
        int result;
        if (!StringUtils.hasText(hName)) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }
        String hId = managerHurdles.getHId();
        //如果存在id，就是修改操作
        if (StringUtils.hasText(hId)) {
            if (managerHurdlesMapper.selectByPrimaryKey(hId) == null) {
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
            result = managerHurdlesMapper.updateByPrimaryKey(managerHurdles);
        } else {
            managerHurdles.setHId(UUID.randomUUID().toString().replace("-", ""));
            managerHurdles.setHSaved(0);
            managerHurdles.setHFull("未满");
            result = managerHurdlesMapper.insert(managerHurdles);
        }
        // 添加或者修改失败
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public List<ManagerHurdles> queryAllEnable() {
        return managerHurdlesMapper.findAllEnable();
    }
}
