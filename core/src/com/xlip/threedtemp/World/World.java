package com.xlip.threedtemp.World;

import static com.badlogic.gdx.Gdx.gl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Shader.MyShaderProgram;
import com.xlip.threedtemp.World.States.GameState;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by Arif on 13.07.2017.
 */

public class World implements MyInputProcessor.MyInputCallback {
    public PerspectiveCamera camera;
    public ModelBatch modelBatch;
    public Array<GameObject> objects;

    public Environment environment;

    private MyShaderProgram myShaderProgram;
    private FrameBuffer frameBuffer;
    private SpriteBatch spriteBatch;
    private float cameraFar;
    private GameState gameState;

    @Getter
    @Setter
    protected Color clearColor = new Color(0.2f,0.2f,0.2f,1);

    public World() {
        environment = new Environment();
        camera=new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.near=1.5f;
        this.cameraFar = 100;
        camera.far=cameraFar;
        camera.position.set(0,10,-15);
        camera.lookAt(0,0,0);
        camera.update();
        //setMyShaderProgram(new MyShaderProgram(Assets.defaultShaderVertex,Assets.barrelBlurFragment));



        this.gameState = GameState.READY;
        objects=new Array<GameObject>();

        modelBatch = new ModelBatch();
    }

    public void render(float delta) {
        if(myShaderProgram != null) {
            frameBuffer.begin();
        }

        for (GameObject g :
                objects) {
            g.update(delta);
        }

        for (GameObject g :
                objects) {
            if(g.isDisposed()){
                objects.removeValue(g,false);
                g.dispose();
            }

        }
        camera.update();

        gl.glClearColor(clearColor.r,clearColor.g,clearColor.b,1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera);

        modelBatch.render(objects,environment);

        modelBatch.end();

        if(myShaderProgram != null) {
            frameBuffer.end();
            TextureRegion t = new TextureRegion(frameBuffer.getColorBufferTexture());
            t.flip(false,true);
            myShaderProgram.update(delta);
            spriteBatch.setShader(myShaderProgram);
            spriteBatch.begin();
            spriteBatch.draw(t,0,0);
            spriteBatch.end();

        }



    }


    //region Input

    Vector2 down;
    
    
    @Override
    public boolean touchDown(Vector2 cxy) {

        return false;
    }

    MyShaderProgram temp;
    @Override
    public boolean touchUp(Vector2 cxy) {

        return false;
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {

        return false;
    }

    @Override
    public boolean mouseMoved(Vector2 cxy) {
        return false;
    }

    //endregion


    public interface ScreenCallbacks {
        Menu getMenu();

    }

    public void setMyShaderProgram(MyShaderProgram myShaderProgram) {
        this.frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true);
        this.spriteBatch = new SpriteBatch();
        spriteBatch.enableBlending();
        this.myShaderProgram = myShaderProgram;
        this.temp = myShaderProgram;
    }

    public float getCameraFar() {
        return cameraFar;
    }

    public void setCameraFar(float cameraFar) {
        this.cameraFar = cameraFar;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
