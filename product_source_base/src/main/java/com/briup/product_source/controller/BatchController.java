package com.briup.product_source.controller;

import com.briup.product_source.pojo.ManagerBatch;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.ManagerBatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hlmove
 */
@Api(tags = "批次记录模块")
@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {
    private final ManagerBatchService managerBatchService;


    @ApiOperation("查询出所有未检疫的批次")
    @GetMapping("/queryAllUnquarantined")
    public Result queryAllUnquarantined() {
        List<ManagerBatch> result = managerBatchService.queryAllUnquarantined();
        return Result.success(result);
    }

    @ApiOperation("查询所有批次")
    @GetMapping("/queryAll")
    public Result queryAll() {
        List<ManagerBatch> result = managerBatchService.queryAll();
        return Result.success(result);
    }
}
