package com.xlip.comrotator.instance;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Utils.Lerp;
import com.xlip.threedtemp.Utils.VectorLerp;

public class Pointer extends GameObject<Pointer> {
    VectorLerp dirArranger;

    public Pointer() {
        super("pointer");
        setColor(Color.valueOf("999CC2"));
        this.dirArranger = new VectorLerp(0,0,0,0,0,0,120);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        resetAngle();
        transform.rotateTowardTarget(dirArranger.getValue(delta),Vector3.Y);


    }
    Vector3 lastTarget = new Vector3(0,0,0);

    public void lookAt(Vector3 target) {


        dirArranger.go(lastTarget.x,lastTarget.y,lastTarget.z,target.x,target.y,target.z);
        this.lastTarget = target;


    }



    void resetAngle() {
        transform.idt();
        setPosition(getPosition());
    }
}
