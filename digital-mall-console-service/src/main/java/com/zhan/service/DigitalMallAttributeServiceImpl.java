package com.zhan.service;

import com.zhan.dao.DigitalMallAttributeMapper;
import com.zhan.model.DigitalMallAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attributeService")
public class DigitalMallAttributeServiceImpl implements DigitalMallAttributeService {

    private DigitalMallAttributeMapper digitalMallAttributeMapper;

    @Autowired
    public DigitalMallAttributeServiceImpl(DigitalMallAttributeMapper digitalMallAttributeMapper) {
        this.digitalMallAttributeMapper = digitalMallAttributeMapper;
    }

    @Override
    public int insert(DigitalMallAttribute attr) {
        digitalMallAttributeMapper.insert(attr);
        return attr.getId();
    }
}
