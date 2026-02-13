package com.badlogic.nova.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontFactory {

    public static BitmapFont simpleFont() {
        FreeTypeFontGenerator fontGenerator;
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font/arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        BitmapFont font = fontGenerator.generateFont(parameter);
        fontGenerator.dispose();
        return font;
    }

}
