package com.briup.product_source.dao;

import com.briup.product_source.pojo.BaseAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Hlmove
 */
@Repository
public interface BaseAccountMapper {
    BaseAccount selectAccountInfo(@Param("username") String username,
                                 @Param("password") String password);
    BaseAccount selectByPrimaryKey(String accountId);
}
