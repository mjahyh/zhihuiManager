package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerFenceHouse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerFenceHouseMapper {

    List<ManagerFenceHouse> queryAllHouses(String fhName);

    ManagerFenceHouse selectByFhName(String fhName);

    ManagerFenceHouse selectByPrimaryKey(String fhId);

    int updateByPrimaryKey(ManagerFenceHouse managerFenceHouse);

    int insert(ManagerFenceHouse managerFenceHouse);

    int deleteByPrimaryKey(String fhId);

    int deleteBatch(List<String> ids);

    List<ManagerFenceHouse> findAll();

    List<Object> countAll();
}
