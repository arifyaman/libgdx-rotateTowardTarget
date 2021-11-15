package com.xlip.threedtemp.Screen.splashScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Menu.Effects.Defaults.DefaultMenuFinisher;
import com.xlip.threedtemp.Menu.Effects.Defaults.DefaultMenuOpener;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Screen.Screen;
import com.xlip.threedtemp.Settings.Settings;

import java.util.TimerTask;

/**
 * Created by Arif on 6.08.2017.
 */

public abstract class SplashScreen extends Screen  {

    SplashScreenProgressBar progressBar;


    public SplashScreen(final long delaySeconds, final MainCallBacks mainCallBacks) {
        super(null, null);
        final Menu splash = new Menu();
        MenuObject splashImage = new MenuObject(new TextureRegion(Assets.splash), new Vector2(-Settings.orto_width / 2, -Settings.orto_height / 2), new Vector2(Settings.orto_width, Settings.orto_height));
        splash.addMenuObject(splashImage);
        splash.setMenuOpener(new SplashMenuOpener(1800));


        splash.setMenuFinisher(new DefaultMenuFinisher(2500) {
            @Override
            public void onFinished() {
                super.onFinished();
                mainCallBacks.onSplashScreenFinished();
            }
        });

        progressBar = new SplashScreenProgressBar();
        //splash.addMenuObject(progressBar);


        setMenu(splash);


        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (doInBackGround()) {
                            splash.finish();
                        }
                    }
                });
            }
        }, delaySeconds * 1000);


    }


    public abstract boolean doInBackGround();


    public interface MainCallBacks {
        void onSplashScreenFinished();
    }

    private class SplashMenuOpener extends DefaultMenuOpener {

        public SplashMenuOpener(float interpolation) {
            super(interpolation);
            Vector3 viewScale = orthographicCamera.combined.getScale(new Vector3());
            setStartx(viewScale.x);
            setStarty(viewScale.y);
        }
    }


}
