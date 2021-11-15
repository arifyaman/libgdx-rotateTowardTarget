package com.xlip.threedtemp.Objects.input.movement;

import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by arif on 06.01.2018.
 */

public class LerpMovementController extends DragMovementController {
    private Lerp lx,ly,lz;

    public LerpMovementController(GameObject gameObject, float interplationX, float interplationY, float interplationZ) {
        super(gameObject);
        lx = new Lerp(pos.x,pos.x,interplationX);
        ly = new Lerp(pos.y,pos.y,interplationY);
        lz = new Lerp(pos.z,pos.z,interplationZ);
    }

    public LerpMovementController(GameObject gameObject) {
        this(gameObject,11,11,11);
    }

    public void resetLerps() {
        lx.go(pos.x);
        ly.go(pos.y);
        lz.go(pos.z);
    }

    @Override
    public boolean touchDown(Vector2 cxy) {
        super.touchDown(cxy);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 cxy) {
        super.touchUp(cxy);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {
        super.dragged(cxy);
        lx.addToEnd(moveX);
        ly.addToEnd(moveY);
        lz.addToEnd(moveZ);


        return false;
    }

    public void go(float x, float y, float z ) {
        lx.go(x);
        ly.go(y);
        lz.go(z);
    }

    @Override
    public void update(float delta) {
        pos.x = lx.getValue(delta);
        pos.y = ly.getValue(delta);
        pos.z = lz.getValue(delta);
        super.update(delta);
    }
}
