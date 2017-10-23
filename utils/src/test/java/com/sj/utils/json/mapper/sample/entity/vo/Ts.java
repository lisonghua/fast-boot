package com.sj.utils.json.mapper.sample.entity.vo;


import com.sj.utils.json.mapper.JsonKey;

import java.util.List;

/**
 * Created by yangrd on 2017/6/30.
 */
public class Ts {

    @JsonKey("items[2].imeis[].name")
    private List<String> strings;

    public List<String> getStrings() {
        return strings;
    }

    public Ts setStrings(List<String> strings) {
        this.strings = strings;
        return this;
    }
}
