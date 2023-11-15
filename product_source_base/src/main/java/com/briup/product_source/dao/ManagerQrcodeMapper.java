package com.briup.product_source.dao;


import com.briup.product_source.pojo.ManagerQrcode;
import org.springframework.stereotype.Repository;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerQrcodeMapper {
    int deleteByPrimaryKey(Integer qId);

    int insert(ManagerQrcode record);

    int insertSelective(ManagerQrcode record);

    ManagerQrcode selectByPrimaryKey(Integer qId);

    int updateByPrimaryKeySelective(ManagerQrcode record);

    int updateByPrimaryKey(ManagerQrcode record);

    ManagerQrcode getECodeByAnimalId(String id);
}
