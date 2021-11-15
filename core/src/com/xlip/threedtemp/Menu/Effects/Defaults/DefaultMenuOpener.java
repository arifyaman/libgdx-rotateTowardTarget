package com.xlip.threedtemp.Menu.Effects.Defaults;

import com.badlogic.gdx.math.Matrix4;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by Arif on 23.07.2017.
 */

public class DefaultMenuOpener extends com.xlip.threedtemp.Menu.Effects.MenuEffect {
    Lerp fx,fy;
    private float xdif,ydif;
    private float startx, starty;

    public DefaultMenuOpener(float interpolation) {
        super(interpolation);
        this.xdif = 0.0005f;
        this.ydif = 0.0002f;
        this.startx = 0;
        this.starty = 0;

    }
    

    @Override
    public void setView(Matrix4 view) {

        super.setView(view);
        float xdif = this.xdif;
        float ydif = this.ydif;


        fx = new Lerp(startx, viewScale.x + xdif, interpolation) {
            @Override
            public void onFinished() {
                DefaultMenuOpener.this.onFinished();
            }
        }.combineWith(viewScale.x,interpolation);

        fy = new Lerp(starty, viewScale.y + ydif, interpolation).combineWith(viewScale.y,interpolation);

    }

    @Override
    public void tick(float delta) {
        view.setToScaling(fx.getValue(delta),fy.getValue(delta),0);

    }

    // ############## GETTER & SETTER ##################

    public Lerp getFx() {
        return fx;
    }

    public void setFx(Lerp fx) {
        this.fx = fx;
    }

    public Lerp getFy() {
        return fy;
    }

    public void setFy(Lerp fy) {
        this.fy = fy;
    }

    public float getXdif() {
        return xdif;
    }

    public void setXdif(float xdif) {
        this.xdif = xdif;
    }

    public float getYdif() {
        return ydif;
    }

    public void setYdif(float ydif) {
        this.ydif = ydif;
    }

    public float getStartx() {
        return startx;
    }

    public void setStartx(float startx) {
        this.startx = startx;
    }

    public float getStarty() {
        return starty;
    }

    public void setStarty(float starty) {
        this.starty = starty;
    }
}
