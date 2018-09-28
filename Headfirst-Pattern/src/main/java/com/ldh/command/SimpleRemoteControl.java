package com.ldh.command;

/**
 * @author ldh
 * @desc
 * @date 2017/11/5
 */
public class SimpleRemoteControl {
    Command command;

    public SimpleRemoteControl() {
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonWasPressed() {
        command.excute();
    }
}
