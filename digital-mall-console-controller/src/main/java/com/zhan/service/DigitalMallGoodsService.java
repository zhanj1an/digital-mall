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

    public Integer deleteGoods(int id){
        return restTemplate.getForObject("http://console-service/deleteGoods?id=" + id, Integer.class);
    }

    public Integer showGoods(int id){
        return restTemplate.getForObject("http://console-service/showGoods?id=" + id, Integer.class);
    }

    public Integer hideGoods(int id){
        return restTemplate.getForObject("http://console-service/hideGoods?id=" + id, Integer.class);
    }

    public DigitalMallGoods getGoodsById(int id){
        return restTemplate.getForObject("http://console-service/getGoodsById?id=" + id, DigitalMallGoods.class);
    }

    public void updateGoods(DigitalMallGoods goods){
        restTemplate.getForObject("http://console-service/updateGoods" +
                "?id=" + goods.getId() +
                "&name=" + goods.getName() +
                "&categoryId=" + goods.getCategoryId() +
                "&brandId=" + goods.getBrandId() +
                "&imageUrl=" + goods.getImageUrl() +
                "&introduce=" + goods.getIntroduce() +
                "&desUrl=" + goods.getDesUrl() +
                "&saleNum=" + goods.getSaleNum() +
                "&isShow=" + goods.getIsShow() +
                "&isDelete=" + goods.getIsDelete() , void.class);
    }
}
