package com.badlogic.nova.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MonsterFactory {

    World world;

    public MonsterFactory(World world){
        this.world = world;
    }

    public Body createMonster(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(0, 2));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        Shape shape = new CircleShape();
        shape.setRadius(0.4f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        shape.dispose();

        body.setUserData("Monster");
        body.createFixture(fixtureDef);

        return body;
    }

}
