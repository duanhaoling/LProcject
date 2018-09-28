package com.ldh.state;

/**
 * hello world
 *
 * @author ldh
 * @date 2018/2/4
 */
public class HasQuarterState implements State {
    GumBallMachine gumBallMachine;

    public HasQuarterState(GumBallMachine gumBallMachine) {
        this.gumBallMachine = gumBallMachine;
    }

    @Override
    public void insertQuarter() {

    }

    @Override
    public void ejectQuarter() {

    }

    @Override
    public void turnCrank() {

    }

    @Override
    public void dispense() {

    }
}
