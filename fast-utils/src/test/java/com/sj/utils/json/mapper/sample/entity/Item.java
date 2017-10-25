package com.sj.utils.json.mapper.sample.entity;


import com.sj.utils.json.mapper.JsonKey;

import java.util.Date;
import java.util.List;

/**
 * Created by yangrd on 2017/6/29.
 */
public class Item {

    @JsonKey("skuId")
    private String name;

    @JsonKey("name")
    private String skuId;

    private Date createTime;

    private List<IMEI> imeis;

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getSkuId() {
        return skuId;
    }

    public Item setSkuId(String skuId) {
        this.skuId = skuId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Item setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<IMEI> getImeis() {
        return imeis;
    }

    public Item setImeis(List<IMEI> imeis) {
        this.imeis = imeis;
        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", skuId='" + skuId + '\'' +
                ", createTime=" + createTime +
                ", imeis=" + imeis +
                '}';
    }
}
