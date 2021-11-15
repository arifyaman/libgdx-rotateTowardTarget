package com.xlip.threedtemp.Utils;

/**
 * Created by Arif on 20.07.2017.
 */

public class Lerp {
    protected float orjStart;
    protected float start;
    protected float end;
    protected float interpolation;
    protected  boolean finished;
    protected Lerp combined;
    protected float finisherFraction;
    protected boolean looped;



    public Lerp(float start, float end, float interpolation) {
        this.start = start;
        this.orjStart = start;
        this.end = end;
        this.interpolation = interpolation;
        finished = false;
        this.finisherFraction = 100;
    }


    public float getValue(float delta) {

        if(isFinished())
            onFinished();

        if(!this.finished) {

            if (Math.abs(end - start) < Math.abs(orjStart-end)/finisherFraction || end == start) {
                start = end;
                finished = true;

            }

            start += (end - start) * interpolation * delta / finisherFraction;

            return start;
        }else {
            if(combined != null) {
                return combined.getValue(delta);
            }

            return end;
        }
    }

    public Lerp combineWith(float end) {
        combineWith(end, interpolation);
        return this;
    }

    public Lerp loop() {
        this.looped = true;
        return this;
    }

    public Lerp combineWith(float end, float interpolation){
        if(combined == null) {
            combined = new Lerp(this.end,end,interpolation);
        }else {
            combined.combineWith(end,interpolation);
        }

        return this;
    }

    public void addToEnd(float difference) {
        this.end += difference;
        finished = false;
    }

    public Lerp go(float end) {
        this.end = end;
        finished = false;
        return this;
    }

    public boolean isFinished() {
        if(combined != null){
            return combined.isFinished();
        }
        return this.finished;
    }

    public void resetChain(){
        reset();

        if(combined != null){
            combined.reset();
        }

    }

    public void reset() {
        this.start = orjStart;
        this.finished = false;
    }

    public float getEnd() {
        return end;
    }

    public void onFinished(){
        if(looped){
            resetChain();
        }
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public float getFinisherFraction() {
        return finisherFraction;
    }

    public Lerp setFinisherFraction(float finisherFraction) {
        this.finisherFraction = finisherFraction;
        return this;
    }

    public float getInterpolation() {
        return interpolation;
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }
}
