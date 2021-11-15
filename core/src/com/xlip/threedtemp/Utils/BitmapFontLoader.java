package com.xlip.threedtemp.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class BitmapFontLoader {

    public static BitmapFont load(FileHandle fnt, FileHandle png){
        Texture fontTexture = new Texture(png);
        fontTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return new BitmapFont(fnt,  new TextureRegion(fontTexture), false);
    }

    public static BitmapFont load(FileHandle fnt, FileHandle png, float scale){
        BitmapFont font = load(fnt,png);
        font.getData().setScale(scale);
        return font;
    }
}
