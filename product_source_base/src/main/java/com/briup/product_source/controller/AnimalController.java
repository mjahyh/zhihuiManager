package com.briup.product_source.controller;

import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.ManagerAnimalService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Hlmove
 */
@Api(tags = "动物管理模块")
@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final ManagerAnimalService managerAnimalService;


    @ApiOperation("分页条件查询动物")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "aHealthy", value = "健康状态", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "aGender", value = "性别", paramType = "query", dataType = "String")
    })
    @GetMapping
    public Result queryByConditionsAndPage(Integer pageNum, Integer pageSize, String aHealthy, String aGender) {
        PageInfo<ManagerAnimalExt> pageInfo = managerAnimalService.queryByConditionsAndPage(pageNum, pageSize, aHealthy, aGender);
        return Result.success(pageInfo);
    }

    @ApiOperation("新增或者修改动物")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@Valid @RequestBody ManagerAnimal animal) {
        managerAnimalService.saveOrUpdate(animal);
        return Result.success();
    }

    @ApiOperation("删除动物")
    @DeleteMapping("/deleteById/{aAnimalId}")
    public Result deleteById(@PathVariable String aAnimalId) {
        managerAnimalService.deleteById(aAnimalId);
        return Result.success();
    }

    @ApiOperation("批量删除动物信息")
    @DeleteMapping("/deleteByIdAll")
    public Result deleteByIdAll(@RequestBody List<String> aAnimalIds) {
        managerAnimalService.deleteByIdAll(aAnimalIds);
        return Result.success();
    }


    @ApiOperation("根据动物ID生成对应的二维码信息接口")
    @GetMapping("/QRcode")
    public Result generateQRCode(String animalId) {
        managerAnimalService.createQRcodeByAnimalId(animalId);
        return Result.success();
    }
    @ApiOperation("根据id查找动物")
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        ManagerAnimalExt  animalExt = managerAnimalService.findById(id);
        return Result.success(animalExt);
    }
}
