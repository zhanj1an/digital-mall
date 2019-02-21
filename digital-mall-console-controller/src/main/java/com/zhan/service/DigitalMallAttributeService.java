package com.zhan.service;

import com.zhan.model.DigitalMallAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("attributeService")
public class DigitalMallAttributeService {

    private RestTemplate restTemplate;

    @Autowired
    public DigitalMallAttributeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Integer insert(DigitalMallAttribute attr){
        return restTemplate.getForObject("http://console-service/addAttribute" +
                "?name=" + attr.getName() +
                "&goodsId=" + attr.getGoodsId() +
                "&updateTime=" + attr.getUpdateTime(), Integer.class);
    }
}
