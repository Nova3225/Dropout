package com.badlogic.nova;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.nova.entity.Player;
import com.badlogic.nova.event.ControllPlayer;
import com.badlogic.nova.event.Input;
import com.badlogic.nova.factory.EffectFactory;

import java.util.ArrayList;

public class FightScreen implements Screen {

    Game game;

    Camera camera;
    Viewport viewport;
    SpriteBatch batch;

    World world;
    Box2DDebugRenderer renderer;

    public Player player;

    ArrayList<Input> listenList = new ArrayList<>();

    ArrayList<ParticleEffect> effectList = new ArrayList<>();

    public FightScreen(Game game){
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(16, 10, camera);
        batch = new SpriteBatch();

        world = new World(new Vector2(0, 0), true);
        renderer = new Box2DDebugRenderer();

        effectList.add(EffectFactory.fireEffect());
    }

    @Override
    public void show() {
        player = new Player(world);
        listenList.add(new ControllPlayer(this));
        for (ParticleEffect i : effectList){
            i.start();
        }
    }

    @Override
    public void render(float delta) {
        input(delta);
        logic(delta);
        draw(delta);
    }

    private void input(float delta) {
        for (Input i : listenList){
            i.listen();
        }
    }

    private void logic(float delta) {
        for (ParticleEffect i : effectList){
            i.setPosition(player.body.getPosition().x, player.body.getPosition().y);
            i.update(delta);
        }
        if (player.body.getPosition().x >= viewport.getWorldWidth() / 2){
            player.body.setTransform(-viewport.getWorldWidth()/2, player.body.getPosition().y, player.body.getAngle());
        }
    }

    private void draw(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);


        batch.begin();
        for (ParticleEffect i : effectList){
            i.draw(batch);
        }
        batch.end();

        renderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);

        drawEffect(delta);
    }

    private void drawEffect(float delta){
        for (ParticleEffect i : effectList){
            i.setPosition(player.body.getPosition().x, player.body.getPosition().y);
            if (i.isComplete()) {
                i.reset();
                i.start();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        if(width <= 0 || height <= 0) return;
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
