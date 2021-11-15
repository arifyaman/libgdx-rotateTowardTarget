package com.xlip.threedtemp.Objects.input.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Objects.GameObject;

/**
 * Created by arif on 06.01.2018.
 */

public abstract class BaseMovementController {
    public Vector3 pos;
    public GameObject gameObject;

    public BaseMovementController(GameObject gameObject) {
        this.gameObject = gameObject;
        this.pos = gameObject.getPosition();
    }


    public abstract boolean touchDown(Vector2 cxy);

    public abstract boolean touchUp(Vector2 cxy);

    public abstract boolean touchDragged(Vector2 cxy);

    public void process(Vector3 newPosition) {
        pos = newPosition;
    }

    public void update(float delta) {
        gameObject.setPosition(pos);
    }


}
