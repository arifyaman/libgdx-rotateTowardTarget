package com.xlip.threedtemp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Arif on 15.07.2017.
 */

public class Assets {

    public static Texture splash;
    public static Texture dot;


    //endregion
    
    
    public static void init() {

        splash = new Texture("splash.png");

        splash.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        dot = new Texture("dot.png");
    }




}
