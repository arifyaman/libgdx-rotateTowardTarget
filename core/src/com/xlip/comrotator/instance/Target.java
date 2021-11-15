package com.xlip.comrotator.instance;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Utils.Lerp;

import lombok.Getter;
import lombok.Setter;

public class Target extends GameObject<Target> {

    @Getter
    @Setter
    Vector3 targetPos = new Vector3();


    public Target() {
        super("target");
        setColor(Color.valueOf("F45B69"));
    }


    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
