package com.badlogic.nova;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.nova.event.Input;
import com.badlogic.nova.event.PressToStart;
import com.badlogic.nova.factory.FontFactory;

import java.util.ArrayList;

public class FirstScreen implements Screen {

    public Game game;

    Camera camera;
    Viewport viewport;
    SpriteBatch batch;

    BitmapFont font;

    //监听列表
    ArrayList<Input> listenList = new ArrayList<>();

    public FirstScreen(Game game){
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        batch = new SpriteBatch();

        font = FontFactory.simpleFont();
    }

    @Override
    public void show() {
        //添加空格监听
        listenList.add(new PressToStart(this));
    }

    @Override
    public void render(float delta) {
        input(delta);
        logic(delta);
        draw(delta);
    }

    private void input(float delta) {
        //遍历监听列表
        for (Input i : listenList){
            i.listen();
        }
    }

    private void logic(float delta) {

    }

    private void draw(float delta) {
        ScreenUtils.clear(Color.BROWN);
        camera.update();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch,"Game", -230, 50);
        font.draw(batch,"Press Space to Start", -230, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        if(width <= 0 || height <= 0) return;
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
        font.dispose();
        batch.dispose();

    }
}
