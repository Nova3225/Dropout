package com.badlogic.nova;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.nova.entity.Bullet;
import com.badlogic.nova.entity.Monster;
import com.badlogic.nova.entity.Player;
import com.badlogic.nova.event.BulletLogic;
import com.badlogic.nova.event.ControlPlayer;
import com.badlogic.nova.event.Input;
import com.badlogic.nova.factory.EffectFactory;
import com.badlogic.nova.manager.ContactManager;

import java.util.ArrayList;

public class FightScreen implements Screen {

    Game game;

    Camera camera;
    Viewport viewport;
    SpriteBatch batch;

    public World world;
    Box2DDebugRenderer renderer;

    ContactManager contactManager;

    public Player player;

    ArrayList<Input> listenList = new ArrayList<>();

    ArrayList<Input> removeList = new ArrayList<>();

    ArrayList<ParticleEffect> effectList = new ArrayList<>();

    public ArrayList<Bullet> bullets = new ArrayList<>();

    ArrayList<Monster> monsters = new ArrayList<>();

    public static ArrayList<Body> bodiesToDestory = new ArrayList<>();

    public FightScreen(Game game){
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(16, 10, camera);
        batch = new SpriteBatch();

        world = new World(new Vector2(0, 0), true);
        renderer = new Box2DDebugRenderer();

        contactManager = new ContactManager();
        world.setContactListener(contactManager);
        effectList.add(EffectFactory.fireEffect());

        monsters.add(new Monster(world));

    }

    private void debug(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(0, 0));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        Shape shape = new CircleShape();
        shape.setRadius(0.2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.shape = shape;
        shape.dispose();

        body.setUserData("debug");
        body.createFixture(fixtureDef);
    }

    @Override
    public void show() {
        player = new Player(world);
        listenList.add(new ControlPlayer(this));
        removeList.add(new BulletLogic(this));

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
        if (player.body.getPosition().x >= viewport.getWorldWidth() / 2){
            player.body.setTransform(-viewport.getWorldWidth()/2, player.body.getPosition().y, player.body.getAngle());
        }
        for (Input i : removeList){
            i.listen();
        }
        drawEffect(delta);
    }

    float count = 0;

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

        for (int i = bodiesToDestory.size() - 1; i >= 0; i --){
            world.destroyBody(bodiesToDestory.get(i));
            bodiesToDestory.remove(bodiesToDestory.get(i));
        }

        count += delta;

        if (count >= 1){

            count = 0;
        }


    }

    private void drawEffect(float delta){
        for (ParticleEffect i : effectList){
            i.update(delta);
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
        world.dispose();
        for (ParticleEffect i : effectList){
            i.dispose();
        }
        renderer.dispose();
    }
}
