package com.badlogic.nova.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.nova.FightScreen;

import java.util.logging.Logger;

public class ControllPlayer extends Input {

    Logger log = Logger.getGlobal();

    float velocityX = 0;
    float velocityY = 0;

    public ControllPlayer(Screen screen) {
        super(screen);
    }

    @Override
    public void listen() {
        FightScreen fightScreen = (FightScreen) screen;
        Body body = fightScreen.player.body;
        velocityX = 0;
        velocityY = 0;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) velocityX -= 800f * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) velocityX += 800f * Gdx.graphics.getDeltaTime();
        body.setLinearVelocity(new Vector2(velocityX, velocityY));
    }
}
