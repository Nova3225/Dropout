package com.badlogic.nova.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.nova.FightScreen;
import com.badlogic.nova.entity.Bullet;

public class ControlPlayer extends Input {

    float velocityX = 0;
    float velocityY = 0;

    public ControlPlayer(Screen screen) {
        super(screen);
    }

    float count = 0;

    @Override
    public void listen() {
        FightScreen fightScreen = (FightScreen) screen;
        Body body = fightScreen.player.body;
        velocityX = 0;
        velocityY = 0;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) velocityX -= 800f * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) velocityX += 800f * Gdx.graphics.getDeltaTime();
        body.setLinearVelocity(new Vector2(velocityX, velocityY));
        count += Gdx.graphics.getDeltaTime();
        //if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.E)){
        if (count >= 0.5f){
            fightScreen.bullets.add(new Bullet(fightScreen.world, fightScreen.player));
            count = 0;
        }
    }
}
