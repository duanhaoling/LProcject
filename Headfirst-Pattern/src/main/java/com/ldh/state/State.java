package com.ldh.state;

/**
 * @author ldh
 * @date 2018/2/4
 */
public interface State {
    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
