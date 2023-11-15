package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerBatch;

import java.util.List;

/**
 * @author Hlmove
 */
public interface ManagerBatchService {

    List<ManagerBatch> queryAllUnquarantined();

    List<ManagerBatch> queryAll();

}
