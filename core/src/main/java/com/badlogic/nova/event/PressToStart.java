package com.badlogic.nova.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.nova.FightScreen;
import com.badlogic.nova.FirstScreen;

import java.util.logging.Logger;

public class PressToStart extends Input {

    Logger log = Logger.getGlobal();

    public PressToStart(Screen screen) {
        super(screen);
    }

    @Override
    public void listen() {
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)){
            log.info("切换屏幕到" + "FightScreen");
            ((FirstScreen)screen).game.setScreen(new FightScreen(((FirstScreen)screen).game));
        }
    }
}
