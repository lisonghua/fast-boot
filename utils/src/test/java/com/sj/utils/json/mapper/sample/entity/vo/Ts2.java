package com.sj.utils.json.mapper.sample.entity.vo;

import com.sj.utils.json.mapper.JsonKey;

import java.util.List;

/**
 * Created by yangrd on 2017/6/30.
 */
public class Ts2 {

    @JsonKey("items[2].imeis[]")
    private List<TsItem> tsItems;

    public List<TsItem> getTsItems() {
        return tsItems;
    }

    public Ts2 setTsItems(List<TsItem> tsItems) {
        this.tsItems = tsItems;
        return this;
    }
}
