package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerDisease;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerDiseaseMapper {

    List<ManagerDisease> findAllDisease();

    @MapKey("")
    List<Map<String, Object>> countDisease();
}
