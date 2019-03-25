package com.mygdx.game.managers.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * Handles raw input from the user and translates it into game commands.
 *
 * @author Jerry Springer
 * @version 03 03 2019
 */
public class GameInputAdapter extends InputAdapter {

	/** The listener that is called to handle the raw input. */
	private GameInputHandler listener;

	/** The ID tag for the player that this game input adapter is used for. */
	private int playerId;

	/** The keycode for the forward key. */
	private int forward;
	/** The keycode for the backward key. */
	private int backward;
	/** The keycode for the left key. */
	private int left;
	/** The keycode for the right key. */
	private int right;

	/** If the forward key is being pressed. */
	private boolean forwardPressed;
	/** If the backward key is being pressed. */
	private boolean backwardPressed;
	/** If the left key is being pressed. */
	private boolean leftPressed;
	/** If the right key is being pressed. */
	private boolean rightPressed;

	public GameInputAdapter(final int playerId, final GameInputHandler listener) {
		this(playerId, listener, "W", "S", "A", "D");
	}

	public GameInputAdapter(final int playerId, final GameInputHandler listener,
							final String forward, final String backward,
							final String left, final String right) {

		super();
		this.listener = listener;
		this.playerId = playerId;
		setKeyBinds(forward, backward, left, right);
	}

	/**
	 * Sets the key-bindings for keyboard input.
	 *
	 * @param forward the new key to move forward.
	 * @param backward the new key to move backwards.
	 * @param left the new key to move left.
	 * @param right the new key to move right.
	 */
	public void setKeyBinds(final String forward, final String backward,
							final String left, final String right) {
		this.forward = Input.Keys.valueOf(forward);
		this.backward = Input.Keys.valueOf(backward);
		this.left = Input.Keys.valueOf(left);
		this.right = Input.Keys.valueOf(right);
	}

	@Override
	public boolean keyDown(final int keycode) {
		updateMovementKeys(keycode);
		return true;
	}

	@Override
	public boolean keyUp(final int keycode) {
		updateMovementKeys(keycode);
		return true;
	}

	/**
	 * Updates the keys which are used for movement.
	 *
	 * @param keycode the key pressed.
	 * @return if the key pressed was a movement key.
	 */
	private boolean updateMovementKeys(final int keycode) {

		// Update which keys are being pressed
		if (keycode == forward)
			forwardPressed = !forwardPressed;
		else if (keycode == backward)
			backwardPressed = !backwardPressed;
		else if (keycode == left)
			leftPressed = !leftPressed;
		else if (keycode == right)
			rightPressed = !rightPressed;
		else
			return false;

		// Update movement speeds based on which movement keys are pressed
		Vector2 direction = new Vector2();

		if (forwardPressed == backwardPressed)
			direction.y = 0;
		else if (forwardPressed)
			direction.y = 1;
		else
			direction.y = -1;

		if (leftPressed == rightPressed)
			direction.x = 0;
		else if (leftPressed)
			direction.x = -1;
		else
			direction.x = 1;

		listener.move(playerId, direction);

		return true;
	}

	@Override
	public boolean mouseMoved(final int screenX, final int screenY) {
		listener.look(playerId, screenX, screenY);
		return true;
	}
}
