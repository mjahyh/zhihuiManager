package com.briup.product_source.dao;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Hlmove
 */
@Repository
public interface IssueRecordMapper {
    @MapKey("")
    List<Map<String, Integer>> countSales();

}
