package com.zhan.service;

import com.zhan.model.DigitalMallGoodsAttribute;
import com.zhan.model.DigitalMallGoodsSynopsis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("portalGoodsService")
public class DigitalMallGoodsService {

    private RestTemplate restTemplate;

    @Autowired
    public DigitalMallGoodsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallGoodsSynopsis> getRollGoodsSynopsis(){
        return restTemplate.getForObject("http://portal-service/getRollGoodsSynopsis", List.class);
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallGoodsAttribute> getGoodsAttribute(int goodsId){
        return restTemplate.getForObject("http://portal-service/getGoodsAttribute?goodsId=" + goodsId, List.class);
    }
}
