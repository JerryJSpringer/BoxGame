package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.InputHandler;
import com.mygdx.game.screens.GameScreen;


/**
 * The launch point for the game that is used by the platform specific launcher.
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class MyGdxGame extends Game {

	/** The sprite batch that is used to draw graphics. */
	public SpriteBatch batch;

	/** The input handler which handles player input. */
	public InputHandler inputHandler;

	/**
	 * Creates a new game object.
	 *
	 * @param inputHandler handles the players input.
	 */
	public MyGdxGame(InputHandler inputHandler) {

		this.inputHandler = inputHandler;
	}

	/**
	 * Creates and launches the game.
	 */
	@Override
	public void create () {

		batch = new SpriteBatch();

		this.setScreen(new GameScreen(this, inputHandler));
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
	}
}
