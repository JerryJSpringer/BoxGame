package com.mygdx.game.managers.input;

import com.badlogic.gdx.math.Vector2;

/**
 * The interface for a game input handler which is used to process the raw input
 * from a given player.
 *
 * @author Jerry Springer
 * @version 03 24 2019
 */
public interface GameInputHandler {

	/**
	 * Called when a player has moved.
	 *
	 * @param playerId the identifier for the player.
	 * @param direction the direction moved in.
	 */
	public void move(final int playerId, final Vector2 direction);

	/**
	 * Called when a player looks in a direction.
	 *
	 * @param playerId the identifier for the player.
	 * @param screenX the x position of the cursor.
	 * @param screenY the y position of the cursor.
	 */
	public void look(final int playerId, final int screenX, final int screenY);
}
