package com.badlogic.nova.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.nova.factory.PlayerFactory;

public class Player {

    public Body body;

    public Player(World world){
        PlayerFactory playerFactory = new PlayerFactory(world);

        body = playerFactory.createPlayer();
    }

}
