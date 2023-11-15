package com.briup.product_source.controller;

import com.briup.product_source.result.Result;
import com.briup.product_source.service.AnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Hlmove
 */
@Api(tags = "首页大屏模块")
@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;


    @ApiOperation("查询数据总数")
    @GetMapping("/count")
    public Result countAll() {
        Map<String, List<Object>> result = analysisService.countAll();
        return Result.success(result);
    }

    @ApiOperation("查询售卖情况")
    @GetMapping("/countSales")
    public Result countSales() {
        Map<String, List<Object>> result = analysisService.countSales();
        return Result.success(result);
    }

    @ApiOperation("统计病症状态")
    @GetMapping("/countDisease")
    public Result countDisease() {
        Map<String, Long> result = analysisService.countDisease();
        return Result.success(result);
    }

    @ApiOperation("统计动物体重信息接口")
    @GetMapping("/indexCount")
    public Result countWeight() {
        Map<String, Integer> result = analysisService.countWeight();
        return Result.success(result);
    }
}
