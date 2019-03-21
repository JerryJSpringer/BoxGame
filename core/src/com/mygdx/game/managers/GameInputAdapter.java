package com.mygdx.game.managers;

import ashley.components.BodyComponent;
import ashley.components.LightComponent;
import ashley.components.MovableComponent;
import ashley.components.SpriteComponent;
import ashley.entity.actors.Character;
import box2dLight.Light;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * @author Jerry Springer
 * @version 03 03 2019
 */
public class GameInputAdapter extends InputAdapter {

		private Viewport viewport;

		private Body body;
		private Light light;
		private Sprite sprite;
		private Vector2 velocity;

		private int forward;
		private int backward;
		private int left;
		private int right;

		private boolean forwardPressed;
		private boolean backwardPressed;
		private boolean leftPressed;
		private boolean rightPressed;

		public GameInputAdapter(Character character, Viewport viewport) {
			this(character, viewport, "W", "S", "A", "D");
		}

		public GameInputAdapter(Character character, Viewport viewport,
								String forward, String backward, String left, String right) {
			super();

			this.viewport = viewport;

			body = character.getComponent(BodyComponent.class).body;
			light = character.getComponent(LightComponent.class).light;
			sprite = character.getComponent(SpriteComponent.class).sprite;
			velocity = character.getComponent(MovableComponent.class).velocity;

			setKeyBinds(forward, backward, left, right);
		}

		public void setKeyBinds(final String forward, final String backward, final String left, final String right) {
			this.forward = Input.Keys.valueOf(forward);
			this.backward = Input.Keys.valueOf(backward);
			this.left = Input.Keys.valueOf(left);
			this.right = Input.Keys.valueOf(right);
		}

		@Override
		public boolean keyDown(int keycode) {

			updateKeys(keycode);

			return true;
		}

		@Override
		public boolean keyUp(int keycode) {

			updateKeys(keycode);

			return true;
		}

		private void updateKeys(int keycode) {

			/* Temporary code for movement speed, refactor later. */
			int speed = 6;

			// Update which keys are being pressed
			if (keycode == forward)
				forwardPressed = !forwardPressed;
			else if (keycode == backward)
				backwardPressed = !backwardPressed;
			else if (keycode == left)
				leftPressed = !leftPressed;
			else if (keycode == right)
				rightPressed = !rightPressed;

			// Update movement speeds based on which movement keys are pressed
			if (forwardPressed == backwardPressed)
				velocity.y = 0;
			else if (forwardPressed)
				velocity.y = speed;
			else
				velocity.y = -speed;

			if (leftPressed == rightPressed)
				velocity.x = 0;
			else if (leftPressed)
				velocity.x = -speed;
			else
				velocity.x = speed;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {

			Vector2 position = body.getPosition();

			Vector2 screenPosition = viewport.project(position);

			Vector3 tp = new Vector3(screenX,
					(viewport.getScreenHeight() + viewport.getBottomGutterHeight() + viewport.getTopGutterHeight()) - screenY,
					0);

			float angle = (float) Math.toDegrees(Math.atan2(tp.y - screenPosition.y, tp.x - screenPosition.x));

			// Set rotation of the sprite and the light
			sprite.setRotation(angle);
			light.setDirection(angle);

			return true;
		}
}
