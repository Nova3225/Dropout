package com.badlogic.nova.event;

import com.badlogic.gdx.Screen;

public abstract class Input {

    Screen screen;

    public Input(Screen screen){
        this.screen = screen;
    }

    public abstract void listen();

}
