package com.sj.utils.json.mapper.sample.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by yangrd on 2017/6/29.
 */
public class Order {

    private int id;

    private Integer integer;

    private String name;

    private Date createTime;

    private List<Item> items;

    public String getName() {
        return name;
    }

    public Order setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Order setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public Order setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", integer=" + integer +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", items=" + items +
                '}';
    }
}
