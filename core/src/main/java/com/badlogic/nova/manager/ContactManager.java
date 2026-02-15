package com.badlogic.nova.manager;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.nova.FightScreen;

public class ContactManager implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();
        if (bodyA.getUserData().equals("Bullet") &&
                bodyB.getUserData().equals("Monster")){
            FightScreen.bodiesToDestory.add(bodyB);
        }else if(bodyB.getUserData().equals("Bullet") &&
                bodyA.getUserData().equals("Monster")){
            FightScreen.bodiesToDestory.add(bodyA);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
