package com.sj.common;


import java.util.function.Consumer;

import static com.sj.common.ResultGenerator.error;
import static com.sj.common.ResultGenerator.ok;

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
