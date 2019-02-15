package com.zhan.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("categoryBrandService")
public class DigitalMallCategoryBrandService {

    private RestTemplate restTemplate;

    public DigitalMallCategoryBrandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Integer insert(int categoryId, int brandId){
        return restTemplate.getForObject("http://console-service/insertCategoryBrand?categoryId="
                + categoryId + "&brandId=" + brandId, Integer.class);
    }
}
