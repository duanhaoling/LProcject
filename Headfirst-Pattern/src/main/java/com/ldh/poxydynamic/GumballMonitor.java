package com.ldh.poxydynamic;

import com.ldh.state.GumBallMachine;

/**
 * Created by ldh on 2018/4/20.
 */
public class GumballMonitor {
    GumBallMachine machine;

    public GumballMonitor(GumBallMachine machine) {
        this.machine = machine;
    }

    public void report() {
        System.out.println("Gumball Machine:" + machine.getLocation());
        System.out.println("Currrnt inventory :" + machine.getCount() + " gumballs");
        System.out.println("Current state:" + machine.getState());
    }
}
