package com.briup.product_source.controller;

import com.briup.product_source.pojo.ManagerFenceHouse;
import com.briup.product_source.pojo.ext.ManagerFenceHouseExt;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.FenceHouseService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hlmove
 */
@Api(tags = "栏舍管理模块")
@RestController
@RequestMapping("/fenceHouse")
@RequiredArgsConstructor
public class FenceHouseController {
    private final FenceHouseService managerFenceHouseService;


    @ApiOperation(value = "分页多条件查询栏舍信息接口", notes = "分页条件默认1/10，根据需要修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "fhName", value = "栏舍名称", paramType = "query", dataType = "String")
    })
    @GetMapping
    public Result queryByConditionsAndPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize, String fhName) {
        PageInfo<ManagerFenceHouse> result = managerFenceHouseService.findByPage(pageNum, pageSize, fhName);
        return Result.success(result);
    }

    @ApiOperation("新增或者修改栏舍")
    @PostMapping("/saveOrUpdate")
    public Result updateFenceHouse(@RequestBody ManagerFenceHouse managerFenceHouse) {
        managerFenceHouseService.saveOrUpdate(managerFenceHouse);
        return Result.success();
    }


    @ApiOperation("删除栏舍")
    @DeleteMapping("/{fhId}")
    public Result deleteFenceHouse(@PathVariable String fhId) {
        managerFenceHouseService.deleteById(fhId);
        return Result.success();
    }

    @ApiOperation("批量删除栏舍")
    @DeleteMapping("/deleteByIdAll")
    public Result deleteBatch(@RequestBody List<String> deleteByIds) {
        managerFenceHouseService.deleteBatch(deleteByIds);
        return Result.success();
    }

    @ApiOperation("根据栏舍编号查询栏舍信息以及栏圈信息接口")
    @GetMapping("/{fhId}")
    public Result queryAllInfo(@PathVariable String fhId) {
        ManagerFenceHouseExt result = managerFenceHouseService.findById(fhId);
        return Result.success(result);
    }

    @ApiOperation("查询所有栏舍信息")
    @GetMapping("/queryAll")
    public Result queryAll() {
        return Result.success(managerFenceHouseService.findAll());
    }
}
