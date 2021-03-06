package com.xlip.threedtemp.Utils;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by Arif on 9.08.2017.
 */

public class VectorLerp {
    private Lerp fx,fy,fz;
    Vector3 temp;

    public VectorLerp(float xs,float ys, float zs, float xe, float ye, float ze, float interpolation) {
        fx = new Lerp(xs,xe,interpolation);
        fy= new Lerp(ys,ye,interpolation);
        fz = new Lerp(zs,ze,interpolation);
        temp = new Vector3();

    }
    
    
    public Vector3 getValue(float delta){
        return temp.set(fx.getValue(delta),fy.getValue(delta),fz.getValue(delta));
    }


    public VectorLerp combineWith(float xs, float ys, float zs){
        fx.combineWith(xs);
        fy.combineWith(ys);
        fz.combineWith(zs);
        return this;
    }

    public VectorLerp go(float xs,float ys, float zs,float xe,float ye, float ze){
        fx.orjStart = xs;
        fy.orjStart = ys;
        fz.orjStart = zs;

        return go(xe,ye,ze);
    }

    public VectorLerp go(float xe,float ye, float ze){
        fx.go(xe);
        fy.go(ye);
        fz.go(ze);

        fx.setFinished(false);
        fy.setFinished(false);
        fz.setFinished(false);
        return this;
    }

    public boolean isFinished() {
        return fx.isFinished() && fy.isFinished() && fz.isFinished();
    }

    public VectorLerp combineWith(float xs, float ys, float zs, float interpolation){
        fx.combineWith(xs,interpolation);
        fy.combineWith(ys, interpolation);
        fz.combineWith(zs,interpolation);
        return this;
    }

    public VectorLerp setFinisherFraction(float finisherFraction){
        fx.setFinisherFraction(finisherFraction);
        fy.setFinisherFraction(finisherFraction);
        fz.setFinisherFraction(finisherFraction);
        return this;
    }


}
