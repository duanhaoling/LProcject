package com.ldh.basic.async;

/**
 * Created by ldh on 2018/4/17.
 */
public class NumberRange {
    private volatile int lower, upper;

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setLower(int lower) {
        if (lower > upper)
            throw new IllegalArgumentException("下限不得大于上限");
        this.lower = lower;
    }

    public void setUpper(int upper) {
        if (upper < lower) {
            throw new IllegalArgumentException("上限不得小于下限");
        }
        this.upper = upper;
    }
}
