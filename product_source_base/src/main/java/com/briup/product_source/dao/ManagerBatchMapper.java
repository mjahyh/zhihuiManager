package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerBatch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerBatchMapper {

    List<ManagerBatch> findAllUnquarantined();

    List<ManagerBatch> findAll();


    ManagerBatch selectByPrimaryKey(String aBatchId);

    int updateBQuarantineAndBQualified(String bQualified, String grBatchId);
}
