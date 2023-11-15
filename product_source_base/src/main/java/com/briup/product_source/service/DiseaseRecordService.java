package com.briup.product_source.service;

import com.briup.product_source.pojo.DiseaseRecord;
import com.briup.product_source.pojo.ManagerDisease;
import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hlmove
 */
public interface DiseaseRecordService {

    List<ManagerDisease> queryAllDisease();

    PageInfo<DiseaseRecordExt> queryByConditionsAndPage(Integer pageNum, Integer pageSize, String drStatus, String drDid);

    void saveOrUpdate(DiseaseRecord record);

    void deleteById(Integer drId);

    void deleteBatch(List<Integer> drIds);

}
