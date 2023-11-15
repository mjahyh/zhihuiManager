package com.briup.product_source.service.impl;

import com.briup.product_source.dao.IssueRecordMapper;
import com.briup.product_source.dao.ManagerAnimalMapper;
import com.briup.product_source.dao.ManagerDiseaseMapper;
import com.briup.product_source.dao.ManagerFenceHouseMapper;
import com.briup.product_source.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Hlmove
 */
@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private final ManagerFenceHouseMapper managerFenceHouseMapper;
    private final IssueRecordMapper issueRecordMapper;
    private final ManagerDiseaseMapper managerDiseaseMapper;
    private final ManagerAnimalMapper managerAnimalMapper;


    @Override
    public Map<String, List<Object>> countAll() {
        List<Object> nameList = Arrays.asList("栏舍", "栏圈", "动物", "冷库", "员工");
        List<Object> resourceList = managerFenceHouseMapper.countAll();
        Map<String, List<Object>> map = new HashMap<>();
        map.put("name", nameList);
        map.put("value", resourceList);
        return map;
    }

    @Override
    public Map<String, List<Object>> countSales() {
        // 月份列表
        String[] month = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        List<Object> monthList = Arrays.asList(month);
        // 对应销售总数列表
        List<Integer> sales = new ArrayList<>(Collections.nCopies(12, 0));

        List<Map<String, Integer>> baseData = issueRecordMapper.countSales();
        baseData.forEach(s -> {
            int monthIndex = s.get("month") - 1;
            int saleNum = Integer.parseInt(String.valueOf(s.get("saleNum")));
            sales.set(monthIndex, saleNum);
        });
        Map<String, List<Object>> map = new HashMap<>();
        map.put("name", monthList);
        map.put("value", new ArrayList<>(sales));
        return map;
    }

    @Override
    public Map<String, Long> countDisease() {
        List<Map<String, Object>> mapList = managerDiseaseMapper.countDisease();
        Map<String, Long> map = new HashMap<>();
        mapList.forEach(s ->
                map.put(String.valueOf(s.get("d_name")), (Long) s.get("sum")));
        return map;
    }

    @Override
    public Map<String, Integer> countWeight() {
        Map<String, Integer> map = managerAnimalMapper.countWeight();
        System.out.println("=======================================================");
        System.out.println("map = " + map);
        System.out.println("=======================================================");
        return map;
    }
}
