package com.briup.product_source.dao.ext;

import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerAnimalExtMapper {

    List<ManagerAnimalExt> findByPage(String aHealthy, String aGender);

    ManagerAnimalExt findById(String id);

}
