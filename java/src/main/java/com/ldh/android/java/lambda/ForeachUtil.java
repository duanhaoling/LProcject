package com.ldh.android.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * desc:
 * Created by ldh on 2018/9/28.
 */
public class ForeachUtil {
    public static <T> List<T> foreachAddWithReturn(int num, Function<Integer, List<T>> getFunc) {
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < num; i++) {
            result.addAll(CatchUtil.tryDo(i, getFunc));
        }
        return result;
    }

    public static <T> void foreachDone(List<T> data, Consumer<T> doFunc) {
        for (T part : data) {
            CatchUtil.tryDo(part, doFunc);
        }
    }
}
