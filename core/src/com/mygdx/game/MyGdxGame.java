package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

	/**
	 * Creates a new game object.
	 */
	public MyGdxGame() {
	}

	/**
	 * Creates and launches the game.
	 */
	@Override
	public void create () {

		batch = new SpriteBatch();

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
	}
}
