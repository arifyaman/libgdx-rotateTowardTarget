package com.xlip.threedtemp.Menu.Object.Font;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Utils.LerpO;

/**
 * Created by Arif on 29.07.2017.
 */

public class FontObject extends MenuObject {
    private LerpO fx,fy;


    public FontObject(TextureRegion texture, Vector2 position, Vector2 wh) {
        super(texture, position, wh);
        this.fx = new LerpO(position.x,position.x,11);
        this.fy = new LerpO(position.y,position.y,11);


    }



    public void goToPosition(float x, float y){
        fx.go(x);
        fy.go(y-15).combineWith(y,9);
    }

    public void goToPosition(Vector2 pos){
        goToPosition(pos.x,pos.y);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        setPosition(new Vector2(fx.getValue(delta),fy.getValue(delta)));

    }
}
