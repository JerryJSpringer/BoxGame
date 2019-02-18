package com.mygdx.game.client;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.InputHandler;

/**
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class HtmlInputHandler implements InputHandler {

	public HtmlInputHandler() {
	}

	public boolean isShooting() {

		return false;
	}

	public boolean isReloading() {

		return false;
	}

	public Vector2 getVelocity() {

		return new Vector2();
	}

	public float getAngle(Vector2 position) {

		return 0;
	}

	public void setViewport(Viewport viewport) {

	}
}
