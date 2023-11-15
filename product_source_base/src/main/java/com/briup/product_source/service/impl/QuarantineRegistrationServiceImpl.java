package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerBatchMapper;
import com.briup.product_source.dao.QuarantineRegistrationMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.QuarantineRegistration;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.QuarantineRegistrationService;
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
public class QuarantineRegistrationServiceImpl implements QuarantineRegistrationService {
    private final QuarantineRegistrationMapper quarantineRegistrationMapper;
    private final ManagerBatchMapper managerBatchMapper;


    @Override
    public PageInfo<QuarantineRegistration> queryByConditionsAndPage(Integer pageNum, Integer pageSize, String bQualified, String grMechanism) {
        PageHelper.startPage(pageNum, pageSize);
        List<QuarantineRegistration> list = quarantineRegistrationMapper.findByPage(bQualified, grMechanism);
        return new PageInfo<>(list);
    }

    @Override
    public void saveOrUpdate(QuarantineRegistration qr) {
        // 参数校验
        String grBatchId = qr.getGrBatchId();
        String bQualified = qr.getBQualified();
        if (!StringUtils.hasText(grBatchId) ||
                !StringUtils.hasText(qr.getGrMechanism()) ||
                !StringUtils.hasText(bQualified) ||
                !StringUtils.hasText(qr.getGrImg()) ||
                !StringUtils.hasText(qr.getGrTime())) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }

        int result; // sql语句执行后，返回受影响的行数result
        if (qr.getGrId() != null) {
            // 有id->更新操作
            result = quarantineRegistrationMapper.updateByPrimaryKey(qr);
        } else {
            // 无id->新增操作
            result = quarantineRegistrationMapper.insert(qr);
        }
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }

        // 修改批次表中对应批次的检疫状态和检疫合格状态
        if (managerBatchMapper.updateBQuarantineAndBQualified(bQualified, grBatchId) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void deleteById(String grId) {
        QuarantineRegistration qr = quarantineRegistrationMapper.selectByPrimaryKey(grId);
        if (qr != null) {
            int result = quarantineRegistrationMapper.deleteByPrimaryKey(grId);
            if (result == 0) {
                throw new ServiceException(ResultCode.FAIL);
            }
        } else {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
    }

    @Override
    public void deleteByIdAll(List<String> grIds) {
        List<String> deleteIds = new ArrayList<>();
        for (String grId : grIds) {
            QuarantineRegistration qr = quarantineRegistrationMapper.selectByPrimaryKey(grId);
            if (qr == null) {
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            } else {
                deleteIds.add(grId);
            }
        }
        if (deleteIds.size() == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
        int deleteResult = quarantineRegistrationMapper.deleteBatch(deleteIds);
        if (deleteResult == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }
}
