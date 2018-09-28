package com.ldh.decorator;

/**
 * @author ldh
 * @desc
 * @date 2017/11/5
 */
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "Most Excellent Dart Roast";
    }

    @Override
    public double cost() {
        return super.cost() + 15;
    }
}
