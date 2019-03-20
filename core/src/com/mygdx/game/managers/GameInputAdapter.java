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
		private Character character;

		private Body body;
		private Light light;
		private Sprite sprite;
		private Vector2 velocity;

		private int forward;
		private int backward;
		private int left;
		private int right;

		public GameInputAdapter(Character character, Viewport viewport) {
			this(character, viewport, "W", "S", "A", "D");
		}

		public GameInputAdapter(Character character, Viewport viewport,
								String forward, String backward, String left, String right) {
			super();

			this.character = character;
			this.viewport = viewport;

			body = character.getComponent(BodyComponent.class).body;
			light = character.getComponent(LightComponent.class).light;
			sprite = character.getComponent(SpriteComponent.class).sprite;
			velocity = character.getComponent(MovableComponent.class).velocity;

			this.forward = Input.Keys.valueOf(forward);
			this.backward = Input.Keys.valueOf(backward);
			this.left = Input.Keys.valueOf(left);
			this.right = Input.Keys.valueOf(right);

		}

		@Override
		public boolean keyDown(int keycode) {

			/* Temporary code for movement speed, refactor later. */
			int speed = 6;

			if (keycode == forward)
				velocity.y += speed;

			if (keycode == backward)
				velocity.y -= speed;

			if (keycode == left)
				velocity.x -= speed;

			if (keycode == right)
				velocity.x += speed;

			return true;
		}

		@Override
		public boolean keyUp(int keycode) {

			/* Temporary code for movement speed, refactor later. */
			int speed = 6;

			if (keycode == forward)
				velocity.y = 0;

			if (keycode == backward)
				velocity.y = 0;

			if (keycode == left)
				velocity.x = 0;

			if (keycode == right)
				velocity.x = 0;

			return true;
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
