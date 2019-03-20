package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		int width = 676;
		int height = 676;

		config.samples = 3;
		config.width = width;
		config.height = height;
		config.title = "Game";

		new LwjglApplication(new MyGdxGame(), config);
	}
}
