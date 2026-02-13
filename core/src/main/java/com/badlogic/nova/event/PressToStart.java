package com.badlogic.nova.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.nova.FightScreen;
import com.badlogic.nova.FirstScreen;

public class PressToStart implements Input {

    @Override
    public void listen(Screen firstScreen) {
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)){
            System.out.println(1);
            ((FirstScreen)firstScreen).game.setScreen(new FightScreen(((FirstScreen)firstScreen).game));
        }
    }

}
