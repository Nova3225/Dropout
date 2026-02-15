package com.badlogic.nova.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.nova.factory.BulletFactory;

public class Bullet {

    public Body body;

    public Bullet(World world, Player player){
        BulletFactory bulletFactory = new BulletFactory(world);
        body = bulletFactory.createBullet(player);
    }

}
