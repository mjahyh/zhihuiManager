package com.briup.product_source.dao;

import com.briup.product_source.pojo.QuarantineRegistration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface QuarantineRegistrationMapper {


    List<QuarantineRegistration> findByPage(String bQualified, String grMechanism);

    void updateById(QuarantineRegistration quarantineRegistration);

    int insert(QuarantineRegistration quarantineRegistration);

    QuarantineRegistration selectByPrimaryKey(String grId);

    int deleteByPrimaryKey(String grId);

    int deleteBatch(List<String> deleteIds);

    int updateByPrimaryKey(QuarantineRegistration qr);
}
