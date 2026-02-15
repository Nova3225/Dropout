package com.badlogic.nova.event;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.nova.FightScreen;
import com.badlogic.nova.entity.Bullet;

public class BulletLogic extends Input{

    public BulletLogic(Screen screen) {
        super(screen);
    }

    @Override
    public void listen() {
        FightScreen fightScreen = (FightScreen) screen;
        for (int i = fightScreen.bullets.size() - 1; i >= 0; i--){
            Body body = fightScreen.bullets.get(i).body;
            if (body.getPosition().y >= 5){
                body.setTransform(0, 0, 0);
                fightScreen.world.destroyBody(body);
                fightScreen.bullets.remove(i);
                break;
            }
        }
    }
}
