package com.zhan.service;

import com.zhan.dao.DigitalMallAttributeValueMapper;
import com.zhan.model.DigitalMallAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attributeValueService")
public class DigitalMallAttributeValueServiceImpl implements DigitalMallAttributeValueService{

    private DigitalMallAttributeValueMapper digitalMallAttributeValueMapper;

    @Autowired
    public DigitalMallAttributeValueServiceImpl(DigitalMallAttributeValueMapper digitalMallAttributeValueMapper) {
        this.digitalMallAttributeValueMapper = digitalMallAttributeValueMapper;
    }


    @Override
    public void insert(DigitalMallAttributeValue digitalMallAttributeValue) {
        digitalMallAttributeValueMapper.insert(digitalMallAttributeValue);
    }
}
