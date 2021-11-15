package com.xlip.threedtemp.Objects.input.movement;

import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Objects.GameObject;

import lombok.Data;
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
    private float dragDeltaZ = 0.01f;

    private boolean reversedX;
    private boolean reversedY;
    private boolean reversedZ;
    private boolean isTocuhed;

    public float moveX, moveY,moveZ;


    public Vector2 touched;


    public DragMovementController(GameObject gameObject) {
        super(gameObject);
        isTocuhed = false;
    }

    @Override
    public boolean touchDown(Vector2 cxy) {
        isTocuhed = true;
        touched = cxy;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 cxy) {
        isTocuhed = false;
        touched = cxy;
        return false;
    }

    public void dragged(Vector2 cxy) {
        float diffX = touched.x - cxy.x;
        moveX = diffX * dragDeltaX * (reversedX ? -1 : 1);


        float diffZ = touched.y - cxy.y;
        moveZ = diffZ * dragDeltaZ* (reversedY ? -1 : 1);
        touched = cxy;
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {
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

    public DragMovementController setReversedZ() {
        this.reversedY = true;
        return this;
    }
}
