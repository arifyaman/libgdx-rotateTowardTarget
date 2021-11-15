package com.xlip.comrotator.screen.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.xlip.comrotator.instance.Pointer;
import com.xlip.comrotator.instance.Road;
import com.xlip.comrotator.instance.Target;
import com.xlip.comrotator.instance.sub.Collision;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Objects.input.movement.plain.LerpMovementController;
import com.xlip.threedtemp.World.World;

import java.util.Random;

import lombok.Getter;

public class GameWorld extends World {
    Target target;
    Road road;

    Random rnd = new Random();

    LerpMovementController lerpMovementController;

    CameraInputController cameraInputController;
    @Getter
    float angle = 0;

    Vector3 intersection = new Vector3();
    public GameWorld() {
        super();
        setClearColor(Color.valueOf("5C6199"));
        camera.position.z -= 20;
        camera.position.y += 10;
        camera.far  = 9999;



        target = new Target();
        target.setPosition(0,0,0);
        objects.add(target);
        this.lerpMovementController = new LerpMovementController(800,800) {

            @Override
            public Vector3 update(float delta) {
                Vector3 pos = super.update(delta);
                target.setTargetPos(pos);
                target.setPosition(pos);
                lookAt();
                return pos;
            }
        };
        this.lerpMovementController.setReversedY();
        this.lerpMovementController.setDragDeltaX(0.13f);
        this.lerpMovementController.setDragDeltaY(0.13f);


        for (int i = 0; i < 500; i++) {
            float spread = 8.8f;
            Pointer pointer = new Pointer();
            pointer.setPosition(rnd.nextFloat()*spread * (rnd.nextBoolean() ? -1 : 1),rnd.nextFloat()*spread* (rnd.nextBoolean() ? -1 : 1),rnd.nextFloat()*spread* (rnd.nextBoolean() ? -1 : 1));
           // box.setPosition(0,0,5);
            objects.add(pointer);
        }

        this.road = new Road();
        //objects.add(road);


        this.cameraInputController = new CameraInputController(camera){
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                Ray pickedRay = camera.getPickRay(screenX, screenY);

                 Collision collision = GameWorld.this.road.checkIntersection(pickedRay);
                 if(collision != null) {
                     System.out.println(collision);
                     GameWorld.this.target.setPosition(collision.getBest());
                 }

                //return super.mouseMoved(screenX, screenY);
                return false;
            }
        };


        //Gdx.input.setInputProcessor(cameraInputController);

    }

    @Override
    public void render(float delta) {
        float r = 10;
        float x = MathUtils.sin(angle)*r;
        float y = MathUtils.cos(angle)*r;
        angle += 2*delta;
        //target.transform.setToTranslation(x,y,x);

        float move = 0.8f;

        /*if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            target.go(target.getPosition().x, target.getPosition().y - 2 , target.getPosition().z);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            target.go(target.getPosition().x, target.getPosition().y + 2 , target.getPosition().z);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            target.go(target.getPosition().x- 2, target.getPosition().y  , target.getPosition().z);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            target.go(target.getPosition().x+ 2, target.getPosition().y  , target.getPosition().z);
        }*/
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            lookAt();
        }



        cameraInputController.update();
        lerpMovementController.update(delta);
        super.render(delta);
        //System.out.println(1/delta);

    }

    void lookAt() {
        for(GameObject object : objects){
            if(object instanceof Pointer){
               ((Pointer) object).lookAt(target.getTargetPos());
            }
        }
    }


    @Override
    public boolean touchDown(Vector2 cxy) {
        lerpMovementController.touchDown(cxy);
        return super.touchDown(cxy);
    }

    @Override
    public boolean touchUp(Vector2 cxy) {
        lerpMovementController.touchUp(cxy);
        return super.touchUp(cxy);
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {
        lerpMovementController.touchDragged(cxy);
        return super.touchDragged(cxy);
    }


}
