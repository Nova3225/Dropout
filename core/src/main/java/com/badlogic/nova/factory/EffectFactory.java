package com.badlogic.nova.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class EffectFactory {

    public static ParticleEffect fireEffect(){

        ParticleEffect effect = new ParticleEffect();

        effect.load(
            Gdx.files.internal("particles/trial.p"),   // 粒子配置文件
            Gdx.files.internal("particles")                // 图片文件夹
        );

        effect.scaleEffect(0.01f);
        effect.setPosition(0, -3);

        return effect;
    }

}
