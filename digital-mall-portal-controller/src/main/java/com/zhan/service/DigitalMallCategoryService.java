package com.zhan.service;

import com.zhan.model.DigitalMallCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("portalCategoryService")
public class DigitalMallCategoryService {

    private RestTemplate restTemplate;

    @Autowired
    public DigitalMallCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallCategory> getCategoryList(){
        return restTemplate.getForObject("http://portal-service/getCategoryList", List.class);
    }

}
