package com.briup.product_source.dao.ext;

import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerHurdlesExtMapper {

    List<ManagerHurdlesExt> findByPage(String hName, Integer hMax, String fhName, String hEnable);

}
