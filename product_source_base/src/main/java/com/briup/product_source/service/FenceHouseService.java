package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerFenceHouse;
import com.briup.product_source.pojo.ext.ManagerFenceHouseExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hlmove
 */
public interface FenceHouseService {

    PageInfo<ManagerFenceHouse> findByPage(Integer pageNum, Integer pageSize, String fhName);

    void saveOrUpdate(ManagerFenceHouse managerFenceHouse);

    void deleteById(String fhId);

    void deleteBatch(List<String> deleteByIds);

    ManagerFenceHouseExt findById(String fhId);

    List<ManagerFenceHouse> findAll();

}
