package com.xlip.threedtemp.Objects.input.movement.plain;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by arif on 06.01.2018.
 */

public class LerpMovementController extends DragMovementController {
    private Lerp lx,ly;
    float interplationX;
    float interplationY;

    public LerpMovementController(float interplationX, float interplationY) {
        super();
        lx = new Lerp(pos.x,pos.x,interplationX);
        ly = new Lerp(pos.y,pos.y,interplationY);
    }

    public void resetLerps() {
        lx.go(pos.x);
        ly.go(pos.y);
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


        return false;
    }


    public void go(float x, float y, float z) {
        lx.go(x);
        ly.go(y);
    }

    @Override
    public Vector3 update(float delta) {
        pos.x = lx.getValue(delta);
        pos.y = ly.getValue(delta);
        pos.z = 0;
        return pos;
    }
}
