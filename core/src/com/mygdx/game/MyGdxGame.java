package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.InputHandler;
import com.mygdx.game.screens.GameScreen;


/**
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public InputHandler inputHandler;

	public MyGdxGame(InputHandler inputHandler) {

		this.inputHandler = inputHandler;
	}
	
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
