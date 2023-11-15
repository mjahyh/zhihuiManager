package com.briup.product_source.controller;

import com.briup.product_source.pojo.DiseaseRecord;
import com.briup.product_source.pojo.ManagerDisease;
import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.DiseaseRecordService;
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
@Api(tags = "病症记录模块")
@RestController
@RequestMapping("/diseaseRecord")
@RequiredArgsConstructor
public class DiseaseController {
    private final DiseaseRecordService diseaseRecordService;


    @ApiOperation("分页条件查询所有的检疫")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "drStatus", value = "是否治疗", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "drDId", value = "疾病名称", paramType = "query", dataType = "String")
    })
    @GetMapping
    public Result queryByConditionsAndPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           String drStatus,
                                           String drDId) {
        PageInfo<DiseaseRecordExt> pageInfo = diseaseRecordService.queryByConditionsAndPage(pageNum, pageSize, drStatus, drDId);
        return Result.success(pageInfo);
    }

    @ApiOperation("查询所有病症")
    @GetMapping("/queryAllDisease")
    public Result queryAllDisease() {
        List<ManagerDisease> result = diseaseRecordService.queryAllDisease();
        return Result.success(result);
    }

    @ApiOperation("新增或更新病症记录接口")
    @PostMapping("/saveOrUpdate")
    public Result reviseDiseaseRecord(@RequestBody DiseaseRecord record) {
        diseaseRecordService.saveOrUpdate(record);
        return Result.success();
    }

    @ApiOperation("删除病症记录")
    @DeleteMapping("/deleteById/{drId}")
    public Result deleteById(@PathVariable Integer drId) {
        diseaseRecordService.deleteById(drId);
        return Result.success();
    }

    @ApiOperation("批量删除病症记录")
    @DeleteMapping("/deleteByIdAll")
    public Result deleteByIdAll(@RequestBody List<Integer> drIds) {
        diseaseRecordService.deleteBatch(drIds);
        return Result.success();
    }
}
