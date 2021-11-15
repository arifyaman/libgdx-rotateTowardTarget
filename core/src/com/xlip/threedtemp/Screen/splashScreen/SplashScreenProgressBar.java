package com.xlip.threedtemp.Screen.splashScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Settings.Settings;
import com.xlip.threedtemp.Utils.Lerp;

public class SplashScreenProgressBar extends MenuObject {
    private Lerp lerp;
    private float progress;

    public SplashScreenProgressBar() {
        super(new TextureRegion(Assets.dot), new Vector2(-Settings.orto_width/2, -200), new Vector2(0,15));

        this.progress = 0;
        this.lerp = new Lerp(0, 0, 10);

    }

    public void setProgress(float progress) {
        this.progress = progress;
        this.lerp.go(progress);
    }


    @Override
    public void draw(SpriteBatch batch, float delta) {
        float width = Settings.orto_width * progress / 100;
        getWh().width = width;


        super.draw(batch, delta);
    }
}
