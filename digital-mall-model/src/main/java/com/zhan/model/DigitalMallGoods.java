package com.zhan.model;

import lombok.Data;

import java.util.List;

@Data
public class DigitalMallGoods {
    private Integer id;

    private String name;

    private Integer categoryId;

    private Integer brandId;

    private String imageUrl;

    private List<String> imageUrlList;

    private String introduce;

    private String desUrl;

    private List<String> desUrlList;

    private Integer saleNum;

    private Integer isShow;

    private Integer isDelete;

    private String updateTime;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private DigitalMallGoodsSynopsis goodsSynopsis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getDesUrl() {
        return desUrl;
    }

    public void setDesUrl(String desUrl) {
        this.desUrl = desUrl == null ? null : desUrl.trim();
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}