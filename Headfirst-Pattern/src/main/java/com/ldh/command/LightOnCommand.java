package com.ldh.command;

/**
 * @author ldh
 * @desc
 * @date 2017/11/5
 */
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void excute() {
        light.on();
    }
}
