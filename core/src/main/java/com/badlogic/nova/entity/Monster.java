package com.badlogic.nova.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.nova.factory.MonsterFactory;

public class Monster {

    Body body;

    public Monster(World world){
        MonsterFactory monsterFactory = new MonsterFactory(world);
        body = monsterFactory.createMonster();
    }

}
