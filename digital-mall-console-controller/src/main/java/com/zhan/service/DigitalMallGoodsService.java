package com.zhan.service;

import com.zhan.model.DigitalMallGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("goodsService")
public class DigitalMallGoodsService {

    private RestTemplate restTemplate;

    @Autowired
    public DigitalMallGoodsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallGoods> getGoodsList(){
        return restTemplate.getForObject("http://console-service/getGoodsList", List.class);
    }

    public void addGoods(DigitalMallGoods digitalMallGoods){
        restTemplate.getForObject("http://console-service/addGoods?name=" + digitalMallGoods.getName() +
                "&categoryId=" + digitalMallGoods.getCategoryId() +
                "&brandId=" + digitalMallGoods.getBrandId() +
                "&imageUrl=" + digitalMallGoods.getImageUrl() +
                "&introduce=" + digitalMallGoods.getIntroduce() +
                "&desUrl=" + digitalMallGoods.getDesUrl(), DigitalMallGoods.class);
    }
}
