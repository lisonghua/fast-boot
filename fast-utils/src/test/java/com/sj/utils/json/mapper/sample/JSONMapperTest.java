package com.sj.utils.json.mapper.sample;

import com.alibaba.fastjson.JSON;
import com.sj.utils.json.mapper.JSONMapper;
import com.sj.utils.json.mapper.sample.entity.IMEI;
import com.sj.utils.json.mapper.sample.entity.Item;
import com.sj.utils.json.mapper.sample.entity.Order;
import com.sj.utils.json.mapper.sample.entity.vo.Ts;
import com.sj.utils.json.mapper.sample.entity.vo.Ts2;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangrd on 2017/6/30.
 */
public class JSONMapperTest {

    Order order;


    @Before
    public void before() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<IMEI> imeis = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                IMEI imei = new IMEI();
                imei.setName("imeiName-" + i + "-" + j);
                imeis.add(imei);
            }
            Item item = new Item();
            item.setCreateTime(new Date());
            item.setName("items" + i);
            item.setSkuId("sku" + i);
            item.setImeis(imeis);
            items.add(item);
        }
        order = new Order();
        order.setCreateTime(new Date());
        order.setName("order1");
        order.setItems(items);
    }

    @Test
    public void json() {
        System.out.println(JSON.toJSONString(order));
    }

    @Test
    public void test() {
        String json = "{\"createTime\":1498808013778,\"items\":[{\"createTime\":1498808013777,\"imeis\":[{\"name\":\"imeiName-1-0\"}],\"name\":\"items0\",\"skuId\":\"sku0\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-1-0\"}],\"name\":\"items1\",\"skuId\":\"sku1\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-2-0\"},{\"name\":\"imeiName-2-1\"}],\"name\":\"items2\",\"skuId\":\"sku2\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-3-0\"},{\"name\":\"imeiName-3-1\"},{\"name\":\"imeiName-3-2\"}],\"name\":\"items3\",\"skuId\":\"sku3\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-4-0\"},{\"name\":\"imeiName-4-1\"},{\"name\":\"imeiName-4-2\"},{\"name\":\"imeiName-4-3\"}],\"name\":\"items4\",\"skuId\":\"sku4\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-5-0\"},{\"name\":\"imeiName-5-1\"},{\"name\":\"imeiName-5-2\"},{\"name\":\"imeiName-5-3\"},{\"name\":\"imeiName-5-4\"}],\"name\":\"items5\",\"skuId\":\"sku5\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-6-0\"},{\"name\":\"imeiName-6-1\"},{\"name\":\"imeiName-6-2\"},{\"name\":\"imeiName-6-3\"},{\"name\":\"imeiName-6-4\"},{\"name\":\"imeiName-6-5\"}],\"name\":\"items6\",\"skuId\":\"sku6\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-7-0\"},{\"name\":\"imeiName-7-1\"},{\"name\":\"imeiName-7-2\"},{\"name\":\"imeiName-7-3\"},{\"name\":\"imeiName-7-4\"},{\"name\":\"imeiName-7-5\"},{\"name\":\"imeiName-7-6\"}],\"name\":\"items7\",\"skuId\":\"sku7\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-8-0\"},{\"name\":\"imeiName-8-1\"},{\"name\":\"imeiName-8-2\"},{\"name\":\"imeiName-8-3\"},{\"name\":\"imeiName-8-4\"},{\"name\":\"imeiName-8-5\"},{\"name\":\"imeiName-8-6\"},{\"name\":\"imeiName-8-7\"}],\"name\":\"items8\",\"skuId\":\"sku8\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-9-0\"},{\"name\":\"imeiName-9-1\"},{\"name\":\"imeiName-9-2\"},{\"name\":\"imeiName-9-3\"},{\"name\":\"imeiName-9-4\"},{\"name\":\"imeiName-9-5\"},{\"name\":\"imeiName-9-6\"},{\"name\":\"imeiName-9-7\"},{\"name\":\"imeiName-9-8\"}],\"name\":\"items9\",\"skuId\":\"sku9\"}],\"name\":\"order1\"}\n";
        Ts ts = new JSONMapper().mapper(JSON.parseObject(json), Ts.class);
        ts.getStrings().forEach(System.out::println);
    }


    @Test
    public void test2() {
        String json = "{\"createTime\":1498808013778,\"items\":[{\"createTime\":1498808013777,\"imeis\":[{\"name\":\"imeiName-1-0\"}],\"name\":\"items0\",\"skuId\":\"sku0\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-1-0\"}],\"name\":\"items1\",\"skuId\":\"sku1\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-2-0\"},{\"name\":\"imeiName-2-1\"}],\"name\":\"items2\",\"skuId\":\"sku2\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-3-0\"},{\"name\":\"imeiName-3-1\"},{\"name\":\"imeiName-3-2\"}],\"name\":\"items3\",\"skuId\":\"sku3\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-4-0\"},{\"name\":\"imeiName-4-1\"},{\"name\":\"imeiName-4-2\"},{\"name\":\"imeiName-4-3\"}],\"name\":\"items4\",\"skuId\":\"sku4\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-5-0\"},{\"name\":\"imeiName-5-1\"},{\"name\":\"imeiName-5-2\"},{\"name\":\"imeiName-5-3\"},{\"name\":\"imeiName-5-4\"}],\"name\":\"items5\",\"skuId\":\"sku5\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-6-0\"},{\"name\":\"imeiName-6-1\"},{\"name\":\"imeiName-6-2\"},{\"name\":\"imeiName-6-3\"},{\"name\":\"imeiName-6-4\"},{\"name\":\"imeiName-6-5\"}],\"name\":\"items6\",\"skuId\":\"sku6\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-7-0\"},{\"name\":\"imeiName-7-1\"},{\"name\":\"imeiName-7-2\"},{\"name\":\"imeiName-7-3\"},{\"name\":\"imeiName-7-4\"},{\"name\":\"imeiName-7-5\"},{\"name\":\"imeiName-7-6\"}],\"name\":\"items7\",\"skuId\":\"sku7\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-8-0\"},{\"name\":\"imeiName-8-1\"},{\"name\":\"imeiName-8-2\"},{\"name\":\"imeiName-8-3\"},{\"name\":\"imeiName-8-4\"},{\"name\":\"imeiName-8-5\"},{\"name\":\"imeiName-8-6\"},{\"name\":\"imeiName-8-7\"}],\"name\":\"items8\",\"skuId\":\"sku8\"},{\"createTime\":1498808013778,\"imeis\":[{\"name\":\"imeiName-9-0\"},{\"name\":\"imeiName-9-1\"},{\"name\":\"imeiName-9-2\"},{\"name\":\"imeiName-9-3\"},{\"name\":\"imeiName-9-4\"},{\"name\":\"imeiName-9-5\"},{\"name\":\"imeiName-9-6\"},{\"name\":\"imeiName-9-7\"},{\"name\":\"imeiName-9-8\"}],\"name\":\"items9\",\"skuId\":\"sku9\"}],\"name\":\"order1\"}\n";
        Ts2 ts = new JSONMapper().mapper(JSON.parseObject(json), Ts2.class);
//        ts.getStrings().forEach(System.out::println);
        ts.getTsItems().forEach(tsItem -> System.out.println(tsItem.getName()));
    }
}
