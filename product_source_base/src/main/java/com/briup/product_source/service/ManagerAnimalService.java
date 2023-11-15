package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hlmove
 */
public interface ManagerAnimalService {

    PageInfo<ManagerAnimalExt> queryByConditionsAndPage(Integer pageNum, Integer pageSize, String aHeathy, String aGender);

    void saveOrUpdate(ManagerAnimal animal);

    void deleteById(String aAnimalId);

    void deleteByIdAll(List<String> aAnimalIds);

    void createQRcodeByAnimalId(String animalId);

   ManagerAnimalExt findById(String id);
}
