package com.xlip.threedtemp.Objects.input.movement.plain;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Objects.GameObject;

/**
 * Created by arif on 06.01.2018.
 */

public abstract class BaseMovementController {
    public Vector3 pos;

    public BaseMovementController() {
        pos = new Vector3();
    }


    public abstract boolean touchDown(Vector2 cxy);

    public abstract boolean touchUp(Vector2 cxy);

    public abstract boolean touchDragged(Vector2 cxy);

    public abstract boolean keyDown(Input.Keys key);

    public void process(Vector3 newPosition) {
        pos = newPosition;
    }

    public Vector3 update(float delta) {
        return pos;
    }


}
