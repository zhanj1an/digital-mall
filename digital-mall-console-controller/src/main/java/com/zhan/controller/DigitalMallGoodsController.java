package com.zhan.controller;

import com.zhan.model.DigitalMallGoods;
import com.zhan.service.DigitalMallBrandService;
import com.zhan.service.DigitalMallCategoryBrandService;
import com.zhan.service.DigitalMallCategoryService;
import com.zhan.service.DigitalMallGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class DigitalMallGoodsController {

    private DigitalMallGoodsService digitalMallGoodsService;

    private DigitalMallCategoryService digitalMallCategoryService;

    private DigitalMallBrandService digitalMallBrandService;

    private DigitalMallCategoryBrandService digitalMallCategoryBrandService;

    @Autowired
    public DigitalMallGoodsController(DigitalMallGoodsService digitalMallGoodsService,
                                      DigitalMallCategoryService digitalMallCategoryService,
                                      DigitalMallBrandService digitalMallBrandService,
                                      DigitalMallCategoryBrandService digitalMallCategoryBrandService) {
        this.digitalMallGoodsService = digitalMallGoodsService;
        this.digitalMallCategoryService = digitalMallCategoryService;
        this.digitalMallBrandService = digitalMallBrandService;
        this.digitalMallCategoryBrandService = digitalMallCategoryBrandService;
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/getGoodsList")
    public String getDigitalMallGoodsList(String goodsName, Model model){
        if(goodsName == null || "".equals(goodsName)) {
            model.addAttribute("goodsList", digitalMallGoodsService.getGoodsList());
        }else{
            System.out.println(goodsName);
        }
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "goods-list";
    }

    @RequestMapping("/toAddGoodsView")
    public String toAddGoodsView(Model model){
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        return "goods-add";
    }

    @RequestMapping("/addGoods")
    public String addGoods(DigitalMallGoods goods, String otherCategory, String otherBrand){
        System.out.println("addGoods:" + goods.toString());
        if(otherCategory != null && !"".equals(otherCategory)){
            goods.setCategoryId(digitalMallCategoryService.insert(otherCategory));
        }
        if(otherBrand != null && !"".equals(otherBrand)){
            goods.setBrandId(digitalMallBrandService.insert(otherBrand));
        }
        log.error("add goods info{}", goods.toString());
        digitalMallGoodsService.addGoods(goods);
        if(digitalMallCategoryBrandService.insert(goods.getCategoryId(), goods.getBrandId()) != 1){
            log.error("CategoryBrand insert fail");
        }
        return "redirect:getGoodsList";
    }

    @ResponseBody
    @RequestMapping("/upload")
    public List<String> upload(MultipartFile[] goodsFile){
        List<String> imageList = new ArrayList<>();
        String path = "/Users/oker/project/digital-mall/digital-mall-console-controller/src/main/resources/static/upload";
        for(MultipartFile file : goodsFile) {
            try {
                file.transferTo(new File(path + File.separator+ file.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            imageList.add("\\upload\\" + file.getOriginalFilename());
        }
        return imageList;
    }

    @ResponseBody
    @RequestMapping("/desUpload")
    public List<String> desUpload(MultipartFile[] desFile){
        List<String> desImageList = new ArrayList<>();
        String path = "/Users/oker/project/digital-mall/digital-mall-console-controller/src/main/resources/static/upload";
        for(MultipartFile f : desFile) {
            try {
                f.transferTo(new File(path + File.separator+ f.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            desImageList.add("\\upload\\" + f.getOriginalFilename());
        }
        return desImageList;
    }

    @ResponseBody
    @RequestMapping("/deleteGoods")
    public int deleteGoods(String id){
        return digitalMallGoodsService.deleteGoods(Integer.parseInt(id));
    }

    @RequestMapping("/toUpdateGoodsView")
    public String toUpdateGoodsView(String id, Model model){
        DigitalMallGoods goods = digitalMallGoodsService.getGoodsById(Integer.parseInt(id));
        model.addAttribute("goodsImgList", Arrays.asList(goods.getImageUrl().split(",")));
        model.addAttribute("desImgList", Arrays.asList(goods.getDesUrl().split(",")));
        model.addAttribute("goods", goods);
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "goods-edit";
    }

    @RequestMapping("/updateGoods")
    public String updateGoods(DigitalMallGoods digitalMallGoods){
        digitalMallGoodsService.updateGoods(digitalMallGoods);
        return "redirect:getGoodsList";
    }

    @RequestMapping("/toAddGoodsSkuView")
    public String toAddGoodsSkuView(String id, Model model){
        model.addAttribute("goods", digitalMallGoodsService.getGoodsById(Integer.parseInt(id)));
        return "sku-add";
    }

    @ResponseBody
    @RequestMapping("/showGoods")
    public int showGoods(String id){
        return digitalMallGoodsService.showGoods(Integer.parseInt(id));
    }

    @ResponseBody
    @RequestMapping("/hideGoods")
    public int hideGoods(String id){
        return digitalMallGoodsService.hideGoods(Integer.parseInt(id));
    }
}