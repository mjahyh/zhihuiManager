package com.briup.product_source.dao;

import com.briup.product_source.pojo.DiseaseRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hlmove
 */
@Repository
public interface DiseaseRecordMapper {


    DiseaseRecord selectByPrimaryKey(Integer drId);

    int updateByPrimaryKey(DiseaseRecord record);

    int insert(DiseaseRecord record);

    int deleteByPrimaryKey(Integer drId);

    int deleteBatch(List<Integer> deleteIds);

}
