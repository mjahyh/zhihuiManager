package com.briup.product_source.controller;

import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.HurdlesService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Hlmove
 */
@Api(tags = "栏圈管理模块")
@RestController
@RequestMapping("/hurdle")
@RequiredArgsConstructor
public class HurdleController {
    private final HurdlesService hurdlesService;


    @ApiOperation("查询栏圈中所有的栏圈容量接口")
    @GetMapping("/queryAllMax")
    public Result queryAllMax() {
        return Result.success(hurdlesService.findAllMax());
    }

    @ApiOperation("分页条件查询所有栏圈信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "hName", value = "栏圈名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "hMax", value = "栏圈容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "fhName", value = "栏舍名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "hEnable", value = "是否可用", paramType = "query", dataType = "String")
    })
    @GetMapping
    public Result queryByConditionsAndPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           String hName, Integer hMax, String fhName, String hEnable) {
        PageInfo<ManagerHurdlesExt> pageInfo = hurdlesService.findByPage(pageNum, pageSize, hName, hMax, fhName, hEnable);
        return Result.success(pageInfo);
    }

    @ApiOperation("根据栏圈编号启用禁用")
    @PutMapping("/{hId}/{hEnable}")
    public Result changeStatus(@PathVariable String hId, @PathVariable String hEnable) {
        hurdlesService.changeStatus(hId, hEnable);
        return Result.success();
    }

    @ApiOperation("实现批量启用禁用栏圈")
    @PutMapping
    public Result changeStatusBatch(@RequestBody List<Map<String, String>> hidAndStatus) {
        hurdlesService.modifyStatusBatch(hidAndStatus);
        return Result.success();
    }

    @ApiOperation("新增或者修改栏圈")
    @PostMapping("/saveOrUpdate")
    public Result updateHurdle(@RequestBody ManagerHurdles managerHurdles) {
        hurdlesService.saveOrUpdate(managerHurdles);
        return Result.success();
    }

    @ApiOperation("查询所有未禁用以及未满的栏圈栏圈")
    @GetMapping("/queryAllEnable")
    public Result queryAllEnable() {
        List<ManagerHurdles> result = hurdlesService.queryAllEnable();
        return Result.success(result);
    }
}
