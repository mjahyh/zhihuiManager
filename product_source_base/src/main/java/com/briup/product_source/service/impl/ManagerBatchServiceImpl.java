package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerBatchMapper;
import com.briup.product_source.pojo.ManagerBatch;
import com.briup.product_source.service.ManagerBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hlmove
 */
@Service
@RequiredArgsConstructor
public class ManagerBatchServiceImpl implements ManagerBatchService {
    private final ManagerBatchMapper managerBatchMapper;

    @Override
    public List<ManagerBatch> queryAllUnquarantined() {
        return managerBatchMapper.findAllUnquarantined();
    }

    @Override
    public List<ManagerBatch> queryAll() {
        return managerBatchMapper.findAll();
    }
}
