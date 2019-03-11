package com.zhan.service;

import com.github.pagehelper.PageInfo;
import com.zhan.model.DigitalMallSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("skuService")
public class DigitalMallSkuService {

    private RestTemplate restTemplate;

    @Autowired
    public DigitalMallSkuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void insert(DigitalMallSku sku){
        restTemplate.getForObject("http://console-service/addSku" +
                "?goodsId=" + sku.getGoodsId() +
                "&goodsName=" + sku.getGoodsName() +
                "&attribute=" + sku.getAttribute() +
                "&price=" + sku.getPrice() +
                "&stock=" + sku.getStock() +
                "&updateTime=" + sku.getUpdateTime() +
                "&isDelete=" + 0, void.class);
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallSku> getSkuList(){
        return restTemplate.getForObject("http://console-service/getSkuList", List.class);
    }

    public void updateSku(int id, int stock){
        restTemplate.getForObject("http://console-service/updateSku?id=" + id + "&stock=" + stock, void.class);
    }

    public DigitalMallSku selectSkuById(int id){
        return restTemplate.getForObject("http://console-service/selectSkuById?id=" + id, DigitalMallSku.class);
    }

    public Integer deleteSku(int id){
        return restTemplate.getForObject("http://console-service/deleteSku?id=" + id, Integer.class);
    }

    @SuppressWarnings("unchecked")
    public PageInfo<DigitalMallSku> selectSkuByCriteria(DigitalMallSku sku){
        return restTemplate.getForObject("http://console-service/selectSkuByCriteria" +
                "?isDelete=" + sku.getIsDelete() +
                "&goodsName=" + sku.getGoodsName() +
                "&pageNum=" + sku.getPageNum() +
                "&pageSize=" + sku.getPageSize(), PageInfo.class);
    }
}
