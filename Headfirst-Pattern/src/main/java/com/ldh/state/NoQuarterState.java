package com.ldh.state;

/**
 * hello world
 *
 * @author ldh
 * @date 2018/2/4
 */
public class NoQuarterState implements State {
    GumBallMachine gumBallMachine;

    public NoQuarterState(GumBallMachine gumBallMachine) {
        this.gumBallMachine = gumBallMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You inserted a quarter");
        gumBallMachine.setState(gumBallMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You haven't insert a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned ,but there's no quarter");
    }

    @Override
    public void dispense() {
        System.out.println("You need to pay first");
    }
}
