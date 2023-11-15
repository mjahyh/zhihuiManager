package com.briup.product_source.dao.ext;

import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface DiseaseRecordExtMapper {

    List<DiseaseRecordExt> findByConditionsAndPage(String drStatus, String drDId);

}
