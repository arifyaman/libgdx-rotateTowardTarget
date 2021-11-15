package com.xlip.comrotator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.ScreenUtils;
import com.xlip.comrotator.model.GameModels;
import com.xlip.comrotator.screen.GameScreen;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.ThreeDTemp;

public class CamRotator extends ThreeDTemp {
	GameScreen gameScreen;
	public CamRotator() {
		super();
		dontShowSplashScreen = true;
	}

	@Override
	public void doInSplashScreenBackground() {

		Assets.init();
		this.gameScreen = new GameScreen();
	}

	@Override
	public void onSplashScreenFinished() {
		super.onSplashScreenFinished();
		screen = gameScreen;

	}

	@Override
	public void addModelTheseParts(ModelBuilder modelBuilder) {
		GameModels.init(modelBuilder);
	}

	@Override
	public void create () {
		super.create();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
