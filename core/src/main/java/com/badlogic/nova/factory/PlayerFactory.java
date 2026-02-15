package com.badlogic.nova.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PlayerFactory {

    World world;

    public PlayerFactory(World world){
        this.world = world;
    }

    public Body createPlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(0, -4));
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        Shape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.shape = shape;
        shape.dispose();

        body.createFixture(fixtureDef);

        return body;
    }

}
