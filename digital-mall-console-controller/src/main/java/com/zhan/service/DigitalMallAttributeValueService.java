package com.zhan.service;

import com.zhan.model.DigitalMallAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("attributeValueService")
public class DigitalMallAttributeValueService {

    private RestTemplate restTemplate;

    @Autowired
    public DigitalMallAttributeValueService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void insert(DigitalMallAttributeValue attrValue){
        restTemplate.getForObject("http://console-service/addAttributeValue" +
                "?attrValue=" + attrValue.getAttrValue() +
                "&attrId=" + attrValue.getAttrId() +
                "&goodsId=" + attrValue.getGoodsId() +
                "&updateTime=" + attrValue.getUpdateTime(), void.class);
    }
}
