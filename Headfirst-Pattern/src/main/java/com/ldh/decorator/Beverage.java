package com.ldh.decorator;

/**
 * @author ldh
 * @desc
 * @date 2017/11/5
 */
public class Beverage {
    protected String description;

    private boolean milk;
    private boolean soy;
    private boolean mocha;
    private boolean whip;


    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isMocha() {
        return mocha;
    }

    public void setMocha(boolean mocha) {
        this.mocha = mocha;
    }

    public boolean isWhip() {
        return whip;
    }

    public void setWhip(boolean whip) {
        this.whip = whip;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 计算所有调料的价钱
     *
     * @return
     */
    public double cost() {
        double price = 0;
        if (milk) {
            price += 1;
        }
        if (soy) {
            price += 1;
        }
        if (mocha) {
            price += 1.8;
        }
        if (whip) {
            price += 1.5;
        }

        return price;
    }
}
