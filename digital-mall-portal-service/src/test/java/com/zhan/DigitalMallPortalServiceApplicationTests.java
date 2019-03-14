package com.zhan;

import com.zhan.model.DigitalMallGoods;
import com.zhan.model.DigitalMallGoodsSynopsis;
import com.zhan.portal.service.DigitalMallGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitalMallPortalServiceApplicationTests {

    @Autowired
    DigitalMallGoodsService digitalMallGoodsService;

    @Test
    public void contextLoads() {
        digitalMallGoodsService.selectGoodsByCriteria(null, null, null, 1, 100).getList().forEach(goods ->{
            System.out.println(goods.toString());
        });
    }

}
