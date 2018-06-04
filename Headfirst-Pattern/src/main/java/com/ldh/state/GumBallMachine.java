package com.ldh.state;

/**
 * @author ldh
 * @date 2018/2/4
 */
public class GumBallMachine {
    State soldState;
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State winnerState;

    State state = soldOutState;
    int count = 0;

    String location;

    public GumBallMachine(String location, int numbersGumballs) {
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        winnerState = new WinnerState(this);
        this.count = numbersGumballs;
        this.location = location;
        if (numbersGumballs > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count > 0) {
            count = count - 1;
        }
    }

    public State getSoldState() {
        return soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getState() {
        return state;
    }

    public int getCount() {
        return count;
    }

    public String getLocation() {
        return location;
    }
}
