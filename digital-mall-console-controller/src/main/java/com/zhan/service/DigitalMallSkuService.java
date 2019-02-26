package com.zhan.service;

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
                "&updateTime=" + sku.getUpdateTime(), void.class);
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallSku> getSkuList(){
        return restTemplate.getForObject("http://console-service/getSkuList", List.class);
    }
}
