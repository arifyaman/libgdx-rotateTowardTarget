package com.xlip.threedtemp.Objects.input.movement.plain;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by arif on 06.01.2018.
 */

public class DragMovementController extends BaseMovementController {
    @Getter
    @Setter
    private float dragDeltaX = 0.01f;
    @Getter
    @Setter
    private float dragDeltaY = 0.01f;


    private boolean reversedX;
    private boolean reversedY;

    private boolean isTouched;

    public float moveX, moveY;


    public Vector2 touched;


    public DragMovementController() {
        super();
        isTouched = false;
    }

    @Override
    public boolean touchDown(Vector2 cxy) {
        isTouched = true;
        touched = cxy;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 cxy) {
        isTouched = false;
        touched = cxy;
        return false;
    }


    @Override
    public boolean keyDown(Input.Keys key) {
        return false;
    }

    public void dragged(Vector2 cxy) {
        float diffX = touched.x - cxy.x;
        moveX = diffX * dragDeltaX * (reversedX ? -1 : 1);


        float diffZ = touched.y - cxy.y;
        moveY = diffZ * dragDeltaY * (reversedY ? -1 : 1);
        touched = cxy;
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {
        dragged(cxy);
        pos.add(moveX,moveY,0);
        process(pos);
        return false;
    }

    public DragMovementController setReversedX() {
        this.reversedX = true;
        return this;
    }

    public DragMovementController setReversedY() {
        this.reversedY = true;
        return this;
    }
}
