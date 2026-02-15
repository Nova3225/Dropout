package com.badlogic.nova.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.nova.entity.Player;

public class BulletFactory {

    World world;

    public BulletFactory(World world){
        this.world = world;
    }

    public Body createBullet(Player player){
        BodyDef bodyDef = new BodyDef();
        bodyDef.bullet = true;
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(player.body.getPosition());
        bodyDef.angle = 0;
        bodyDef.gravityScale = 0;
        bodyDef.linearVelocity.set(new Vector2(0, 10f));

        Body bulletBody = world.createBody(bodyDef);

        Shape circleShape = new CircleShape();
        circleShape.setRadius(0.05f);

        FixtureDef sensorDef = new FixtureDef();
        sensorDef.isSensor = true;
        sensorDef.shape = circleShape;
        circleShape.dispose();

        bulletBody.setUserData("Bullet");
        bulletBody.createFixture(sensorDef);

        return bulletBody;
    }
}
