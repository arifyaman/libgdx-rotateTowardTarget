package com.xlip.comrotator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xlip.comrotator.CamRotator;
import com.xlip.threedtemp.Settings.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = Settings.appwidth;
		config.height = Settings.appheight;

		config.x = Settings.x;
		config.y = Settings.y;
		config.samples = 10;


		LwjglApplication application = new LwjglApplication(new CamRotator(), config);

	}
}
