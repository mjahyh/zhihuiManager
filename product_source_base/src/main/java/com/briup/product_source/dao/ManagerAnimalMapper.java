package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerAnimal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerAnimalMapper {

    ManagerAnimal selectByPrimaryKey(String drAnimalId);

    int updateAHealthyByAAnimalId(String aHealthy, String aAnimalId);

    int updateByPrimaryKey(ManagerAnimal animal);

    int insert(ManagerAnimal animal);

    void deleteById(String aAnimalId);

    int deleteBatch(List<String> deleteIds);


    Map<String, Integer> countWeight();
}
