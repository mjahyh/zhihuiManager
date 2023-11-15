package com.briup.product_source.dao.ext;

import com.briup.product_source.pojo.ext.ManagerFenceHouseExt;
import org.springframework.stereotype.Repository;

/**
 * @author Hlmove
 */
@Repository
public interface ManagerFenceHouseExtMapper {

    ManagerFenceHouseExt selectHouseAndHurdlesById(String id);

}
