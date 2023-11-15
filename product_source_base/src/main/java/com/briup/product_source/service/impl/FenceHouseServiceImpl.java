package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerFenceHouseMapper;
import com.briup.product_source.dao.ManagerHurdlesMapper;
import com.briup.product_source.dao.ext.ManagerFenceHouseExtMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.ManagerFenceHouse;
import com.briup.product_source.pojo.ext.ManagerFenceHouseExt;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.FenceHouseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Hlmove
 */
@Service
@RequiredArgsConstructor
public class FenceHouseServiceImpl implements FenceHouseService {
    private final ManagerFenceHouseMapper managerFenceHouseMapper;
    private final ManagerHurdlesMapper managerHurdlesMapper;
    private final ManagerFenceHouseExtMapper managerFenceHouseExtMapper;


    @Override
    public PageInfo<ManagerFenceHouse> findByPage(Integer pageNum, Integer pageSize, String fhName) {
        // PageHelper开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 核心查询语句
        List<ManagerFenceHouse> managerFenceHouses = managerFenceHouseMapper.queryAllHouses(fhName);
        // 将查询的信息封装在PageInfo对象中，返回
        return new PageInfo<>(managerFenceHouses);
    }

    @Override
    public void saveOrUpdate(ManagerFenceHouse managerFenceHouse) {
        // 参数校验   获取栏舍名称并判断栏舍是否存在
        String fhName = managerFenceHouse.getFhName(); //栏舍名称
        if (managerFenceHouse.getFhTime() == null || !StringUtils.hasText(fhName)) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }

        int result; // sql语句执行后，返回受影响的行数result
        // 根据名称查询栏舍信息
        ManagerFenceHouse house = managerFenceHouseMapper.selectByFhName(fhName);
        String fhId = managerFenceHouse.getFhId(); // 栏圈编号
        if (StringUtils.hasText(fhId)) {
            // 有id->更新操作
            // 判断该条数据是否存在
            if (managerFenceHouseMapper.selectByPrimaryKey(fhId) == null) {
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
            // 判断栏舍名称是否存在
            if (house != null && !house.getFhId().equals(fhId)) {
                throw new ServiceException(ResultCode.FENCE_HOUSE_IS_EXIST);
            }
            // 修改栏舍信息
            result = managerFenceHouseMapper.updateByPrimaryKey(managerFenceHouse);
        } else {
            // 无id->新增操作
            // 判断栏舍名称是否存在
            if (house != null) {
                throw new ServiceException(ResultCode.FENCE_HOUSE_IS_EXIST);
            }
            // 设置栏舍编号
            managerFenceHouse.setFhId(UUID.randomUUID().toString().replace("-", ""));
            // 添加栏舍信息
            result = managerFenceHouseMapper.insert(managerFenceHouse);
        }

        // 添加或者修改失败
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void deleteById(String fhId) {
        ManagerFenceHouse house = managerFenceHouseMapper.selectByPrimaryKey(fhId);
        if (house != null) {
            if (managerHurdlesMapper.selectCountByFhId(fhId) > 0) {
                // 栏舍下有栏圈信息
                throw new ServiceException(ResultCode.DATA_CAN_NOT_DELETE);
            }
            int deleteResult = managerFenceHouseMapper.deleteByPrimaryKey(fhId);
            if (deleteResult == 0) {
                throw new ServiceException(ResultCode.FAIL);
            }
        } else {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
    }

    @Override
    public void deleteBatch(List<String> ids) {
        List<String> deleteIds = ids.stream()
                .filter(s -> !StringUtils.isEmpty(managerFenceHouseMapper.selectByPrimaryKey(s)))
                .filter(s -> managerHurdlesMapper.selectCountByFhId(s) > 0)
                .collect(Collectors.toList());
        int deleteResult = managerFenceHouseMapper.deleteBatch(deleteIds);
        if (deleteResult == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public ManagerFenceHouseExt findById(String id) {
        ManagerFenceHouseExt managerFenceHouseExt = managerFenceHouseExtMapper.selectHouseAndHurdlesById(id);
        if (managerFenceHouseExt == null) {
            throw new ServiceException(ResultCode.FAIL);
        }
        return managerFenceHouseExt;
    }

    @Override
    public List<ManagerFenceHouse> findAll() {
        return managerFenceHouseMapper.findAll();
    }
}
