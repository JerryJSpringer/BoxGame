package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.InputHandler;

/**
 * Used to handle desktop input for basic gameplay.
 *
 * @author Jerry Springer
 * @version 02 09 2019
 */
public class DesktopInputHandler implements InputHandler {

	private Viewport viewport;

	private int forward;
	private int backward;
	private int left;
	private int right;

	public DesktopInputHandler() {
		this("W", "S", "A", "D");
	}

	public DesktopInputHandler(String forward, String backward, String left, String right) {
		this.forward = Input.Keys.valueOf(forward);
		this.backward = Input.Keys.valueOf(backward);
		this.left = Input.Keys.valueOf(left);
		this.right = Input.Keys.valueOf(right);
	}

	public DesktopInputHandler(final int forward, final int backward, final int left, final int right) {
		this.forward = forward;
		this.backward = backward;
		this.left = left;
		this.right = right;
	}

	@Override
	public Vector2 getVelocity() {

		/* Temporary code for movement speed, refactor later. */
		int speed = 6;

		Vector2 velocity = new Vector2();

		if (Gdx.input.isKeyPressed(forward))
			velocity.y += speed;

		if (Gdx.input.isKeyPressed(backward))
			velocity.y -= speed;

		if (Gdx.input.isKeyPressed(left))
			velocity.x -= speed;

		if (Gdx.input.isKeyPressed(right))
			velocity.x += speed;

		return velocity;
	}

	@Override
	public float getAngle(Vector2 position) {

		Vector2 screenPosition = viewport.project(position);

		Vector3 tp = new Vector3(Gdx.input.getX(),
				(viewport.getScreenHeight() + viewport.getBottomGutterHeight() + viewport.getTopGutterHeight()) - Gdx.input.getY(),
				0);

		float angle = (float) Math.toDegrees(Math.atan2(tp.y - screenPosition.y, tp.x - screenPosition.x));

		return angle;
	}

	@Override
	public boolean isShooting() {

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			return true;
		return false;
	}

	@Override
	public boolean isReloading() {
		return true;
	}

	@Override
	public void setViewport(final Viewport viewport) {

		this.viewport = viewport;
	}
}
