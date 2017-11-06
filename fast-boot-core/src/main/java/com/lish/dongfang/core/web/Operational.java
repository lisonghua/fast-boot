package com.lish.dongfang.core.web;


import static com.lish.dongfang.core.web.ResultGenerator.error;
import static com.lish.dongfang.core.web.ResultGenerator.ok;

import java.util.function.Consumer;

/**
 * Created by yangrd on 2017/7/3.
 */
public class Operational {

    public static <T> Result<String> operational(T t, Consumer<T> consumer) {
        try {
            consumer.accept(t);
            return ok();
        } catch (Exception e) {
            return error();
        }
    }
}
