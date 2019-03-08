package com.zhan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhan.dao.DigitalMallSkuMapper;
import com.zhan.model.DigitalMallSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("skuService")
public class DigitalMallSkuServiceImpl implements DigitalMallSkuService {

    private DigitalMallSkuMapper digitalMallSkuMapper;

    @Autowired
    public DigitalMallSkuServiceImpl(DigitalMallSkuMapper digitalMallSkuMapper) {
        this.digitalMallSkuMapper = digitalMallSkuMapper;
    }

    @Override
    public void insert(DigitalMallSku sku) {
        digitalMallSkuMapper.insert(sku);
    }

    @Override
    public List<DigitalMallSku> selectAll() {
        return digitalMallSkuMapper.selectAll();
    }

    @Override
    public void updateSku(int id, int stock) {
        DigitalMallSku sku = digitalMallSkuMapper.selectByPrimaryKey(id);
        sku.setStock(stock);
        digitalMallSkuMapper.updateByPrimaryKey(sku);
    }

    @Override
    public DigitalMallSku selectSkuById(int id) {
        return digitalMallSkuMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer deleteSku(int id) {
        DigitalMallSku sku = digitalMallSkuMapper.selectByPrimaryKey(id);
        sku.setIsDelete(1);
        return digitalMallSkuMapper.updateByPrimaryKey(sku);
    }

    @Override
    public PageInfo<DigitalMallSku> selectSkuByCriteria(String isDelete, String goodsName, int pageNum, int pageSize) {
        DigitalMallSku sku = new DigitalMallSku();
        if(isDelete != null && !"".equals(isDelete) && !"null".equals(isDelete)){
            sku.setIsDelete(Integer.parseInt(isDelete));
        }
        if(goodsName != null && !"".equals(goodsName) && !"null".equals(goodsName)){
            sku.setGoodsName(goodsName);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DigitalMallSku> skuList = digitalMallSkuMapper.selectSkuByCriteria(sku);
        return new PageInfo<>(skuList);
    }
}
