package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerHurdles;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerHurdlesMapper {

    int selectCountByFhId(String fhId);

    List<Integer> findAllMax();

    int changeHEnable(String hId, String hEnable);

    ManagerHurdles selectByPrimaryKey(String hId);

    int updateByPrimaryKey(ManagerHurdles managerHurdles);

    int insert(ManagerHurdles managerHurdles);


    List<ManagerHurdles> findAllEnable();

}
