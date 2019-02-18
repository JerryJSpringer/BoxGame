package com.mygdx.game.managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * @author Jerry Springer
 * @version 02 09 2019
 */
public interface InputHandler {

	boolean isShooting();

	boolean isReloading();

	Vector2 getVelocity();

	float getAngle(Vector2 position);

	void setViewport(Viewport viewport);
}
