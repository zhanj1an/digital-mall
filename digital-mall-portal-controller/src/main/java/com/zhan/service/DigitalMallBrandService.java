package com.zhan.service;

import com.zhan.model.DigitalMallBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("portalBrandService")
public class DigitalMallBrandService {

    private RestTemplate restTemplate;

    @Autowired
    public DigitalMallBrandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallBrand> getBrandList(){
        return restTemplate.getForObject("http://portal-service/getBrandList", List.class);
    }
}
