package com.briup.product_source.service;

import com.briup.product_source.pojo.QuarantineRegistration;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hlmove
 */
public interface QuarantineRegistrationService {


    PageInfo<QuarantineRegistration> queryByConditionsAndPage(Integer pageNum, Integer pageSize, String bQualified, String grMechanism);

    void saveOrUpdate(QuarantineRegistration quarantineRegistration);

    void deleteById(String grId);

    void deleteByIdAll(List<String> grIds);
}
