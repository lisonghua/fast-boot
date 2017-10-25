基于 fastJson 定制json映射

**这里有一个简单的例子**

json 数据如下:
```
{
    "createTime": 1498808290237, 
    "items": [
        {
            "createTime": 1498808290236, 
            "imeis": [ ], 
            "name": "items0", 
            "skuId": "sku0"
        }, 
        {
            "createTime": 1498808290237, 
            "imeis": [
                {
                    "name": "imeiName-1-0"
                }
            ], 
            "name": "items1", 
            "skuId": "sku1"
        }, 
        {
            "createTime": 1498808290237, 
            "imeis": [
                {
                    "name": "imeiName-2-0"
                }, 
                {
                    "name": "imeiName-2-1"
                }
            ], 
            "name": "items2", 
            "skuId": "sku2"
        }
    ], 
    "name": "order1"
}
```
其结构如下：
```
public class Order {

    private int id;

    private Integer integer;

    private String name;

    private Date createTime;

    private List<Item> items;

}

public class Item {

    private String name;

    private String skuId;

    private Date createTime;

    private List<IMEI> imeis;
}    

public class IMEI {

    private String name;

}
```
映射为

**例(1)**
```
public class Ts {

    @JsonKey("items[2].imeis[].name")
    private List<String> strings;
}
```
操作:
```
 String json = "...."
 Ts ts = new JSONMapper().mapper(JSON.parseObject(json), Ts.class);
 ts.getStrings().forEach(System.out::println);
```
打印结果如下:
```
imeiName-2-0
imeiName-2-1
```

**例(2)**
```
public class Ts2 {

    @JsonKey("items[4].imeis[]")
    private List<TsItem> tsItems;
}

public class TsItem {
    private String name;
}        
```
操作:
```
 String json = "...."
 Ts2 ts = new JSONMapper().mapper(JSON.parseObject(json), Ts2.class);
 ts.getTsItems().forEach(tsItem -> System.out.println(tsItem.getName()));
```
打印结果如下:
```
imeiName-2-0
imeiName-2-1
```
**后记:**

JsonKey支持两种简单的语法
```
a
a[]
a[1]
```
并通过.链接构建语法
如:
```
order[].items[].name
```
demo在test目录下
