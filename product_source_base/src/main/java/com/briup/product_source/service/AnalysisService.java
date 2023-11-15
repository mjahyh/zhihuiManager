package com.briup.product_source.service;

import java.util.List;
import java.util.Map;

/**
 * @author Hlmove
 */
public interface AnalysisService {

    Map<String, List<Object>> countAll();

    Map<String, List<Object>> countSales();

    Map<String, Long> countDisease();

    Map<String, Integer> countWeight();

}
