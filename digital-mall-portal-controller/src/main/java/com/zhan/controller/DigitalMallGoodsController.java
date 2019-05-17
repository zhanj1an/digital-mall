package com.zhan.controller;

import com.zhan.dao.DigitalMallUserMapper;
import com.zhan.model.*;
import com.zhan.service.*;
import com.zhan.service.impl.DigitalMallLoginServiceImpl;
import com.zhan.service.impl.DigitalMallOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class DigitalMallGoodsController {

    private DigitalMallGoodsService digitalMallGoodsService;

    private DigitalMallBrandService digitalMallBrandService;

    private DigitalMallCategoryService digitalMallCategoryService;

    private DigitalMallCartService digitalMallCartService;

    private DigitalMallLoginServiceImpl digitalMallLoginService;

    private DigitalMallUserMapper digitalMallUserMapper;

    private DigitalMallOrderServiceImpl digitalMallOrderService;

    @Autowired
    public DigitalMallGoodsController(DigitalMallGoodsService digitalMallGoodsService,
                                      DigitalMallBrandService digitalMallBrandService,
                                      DigitalMallCategoryService digitalMallCategoryService,
                                      DigitalMallCartService digitalMallCartService,
                                      DigitalMallLoginServiceImpl digitalMallLoginService,
                                      DigitalMallUserMapper digitalMallUserMapper,
                                      DigitalMallOrderServiceImpl digitalMallOrderService) {
        this.digitalMallGoodsService = digitalMallGoodsService;
        this.digitalMallBrandService = digitalMallBrandService;
        this.digitalMallCategoryService = digitalMallCategoryService;
        this.digitalMallCartService = digitalMallCartService;
        this.digitalMallLoginService = digitalMallLoginService;
        this.digitalMallUserMapper = digitalMallUserMapper;
        this.digitalMallOrderService = digitalMallOrderService;
    }

    @RequestMapping("/index")
    public String index(Model model, ServletRequest servletRequest){
        model.addAttribute("cartInfo", digitalMallCartService.getCartInfo(servletRequest));
        model.addAttribute("goodsSynopsisList",digitalMallGoodsService.getRollGoodsSynopsis());
        model.addAttribute("topSaleGoodsSynopsisList",digitalMallGoodsService.getTopSaleRollGoodsSynopsis());
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "index";
    }

    @RequestMapping("/searchGoods")
    public String searchGoods(Model model, DigitalMallGoods goods, ServletRequest servletRequest){
        model.addAttribute("cartInfo", digitalMallCartService.getCartInfo(servletRequest));
        model.addAttribute("page", digitalMallGoodsService.selectGoodsByCriteria(goods));
        model.addAttribute("name", goods.getName());
        model.addAttribute("brandId", goods.getBrandId());
        model.addAttribute("categoryId", goods.getCategoryId());
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "store";
    }

    @RequestMapping("/toGoodsView")
    public String toGoodsView(DigitalMallGoodsSynopsis goodsSynopsis, Model model, DigitalMallGoods goods, ServletRequest servletRequest){
        model.addAttribute("cartInfo", digitalMallCartService.getCartInfo(servletRequest));
        model.addAttribute("goodsInfo", new DigitalMallGoodsInfo(goodsSynopsis,
                digitalMallGoodsService.getGoodsAttribute(goodsSynopsis.getGoodsId())));
        model.addAttribute("name", goods.getName());
        model.addAttribute("brandId", goods.getBrandId());
        model.addAttribute("categoryId", goods.getCategoryId());
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "product";
    }

    @ResponseBody
    @RequestMapping("/queryStock")
    public Map<String, String> queryStock(String goodsId, String attr){
        DigitalMallSku sku = digitalMallGoodsService.queryGoodsStock(Integer.parseInt(goodsId), attr);
        Map<String, String> map = new HashMap<>();
        if(sku != null) {
            map.put("price", sku.getPrice() + "");
            map.put("stock", "库存:" + sku.getStock());
            map.put("skuId", sku.getId() + "");
        }
        if(sku != null && sku.getStock() == 0){
            map.put("price", "库存不足");
            map.put("stock", "");
            map.put("skuId", 0 + "");
        }
        if(sku == null){
            map.put("price", "商品已下架");
            map.put("stock", "");
            map.put("skuId", 0 + "");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/addToCart")
    public void addToCart(String number, String skuId, ServletRequest servletRequest, ServletResponse servletResponse){
        digitalMallCartService.addToCart(number, skuId, servletRequest, servletResponse);
    }

    @ResponseBody
    @RequestMapping("/portal-login")
    public int login(ServletResponse servletResponse, String username, String password, ServletRequest servletRequest){
        return Integer.parseInt(digitalMallLoginService.login(servletRequest, servletResponse, username, password));
    }

    @RequestMapping("/toLoginPage")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("/toCheckoutPage")
    public String checkout(ServletRequest servletRequest, Model model){
        int userId = digitalMallLoginService.getLoginStatus(servletRequest);
        if(userId == 0){
            return "login";
        }
        model.addAttribute("account", digitalMallUserMapper.selectByPrimaryKey(userId).getUsername());
        model.addAttribute("cartInfo", digitalMallCartService.getCartInfo(servletRequest));
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "checkout";
    }

    @RequestMapping("/checkout")
    public String checkout(DigitalMallOrder order, ServletRequest servletRequest, ServletResponse servletResponse){
        digitalMallOrderService.insertOrder(order, digitalMallCartService.getCartInfo(servletRequest), servletRequest, servletResponse);
        return "redirect:/index";
    }

    @ResponseBody
    @RequestMapping("/emptyCart")
    public void emptyCart(ServletRequest servletRequest, ServletResponse servletResponse){
        digitalMallCartService.deleteCart(servletRequest, servletResponse);
    }
}
