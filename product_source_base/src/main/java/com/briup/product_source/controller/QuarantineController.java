package com.briup.product_source.controller;

import com.briup.product_source.pojo.QuarantineRegistration;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.QuarantineRegistrationService;
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
@Api(tags = "检疫登记模块")
@RestController
@RequestMapping("/quarantineRegistration")
@RequiredArgsConstructor
public class QuarantineController {
    private final QuarantineRegistrationService quarantineRegistrationService;


    @ApiOperation("分页条件查询所有的检疫")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bQualified", value = "是否合格", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "grMechanism", value = "检疫单位", paramType = "query", dataType = "String")
    })
    @GetMapping
    public Result queryByConditionsAndPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           String bQualified,
                                           String grMechanism) {
        PageInfo<QuarantineRegistration> pageInfo
                = quarantineRegistrationService.queryByConditionsAndPage(pageNum, pageSize, bQualified, grMechanism);
        return Result.success(pageInfo);
    }

    @ApiOperation("添加或者修改信息")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody QuarantineRegistration quarantineRegistration) {
        quarantineRegistrationService.saveOrUpdate(quarantineRegistration);
        return Result.success();
    }

    @ApiOperation("根据id删除信息")
    @DeleteMapping("/deleteById/{grId}")
    public Result deleteById(@PathVariable String grId) {
        quarantineRegistrationService.deleteById(grId);
        return Result.success();
    }

    @ApiOperation("批量删除信息")
    @DeleteMapping("/deleteByIdAll")
    public Result deleteByIdAll(@RequestBody List<String> grIds) {
        quarantineRegistrationService.deleteByIdAll(grIds);
        return Result.success();
    }
}
