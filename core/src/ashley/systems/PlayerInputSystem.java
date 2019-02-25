package ashley.systems;

import ashley.components.*;
import box2dLight.Light;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.managers.InputHandler;

/**
 * The system that updates the play for incoming input.
 *
 * @author Jerry Springer
 * @version 02 09 2019
 */
public class PlayerInputSystem extends EntitySystem {

	/** The array that contains all players. */
	private ImmutableArray<Entity> entities;

	/**
	 * Creates a new input system that responds to player input.
	 * Player input is to be handled separately.
	 */
	public PlayerInputSystem() {
		super();
	}

	/**
	 * Called when a corresponding entity is added to the engine.
	 *
	 * @param engine the engine that contains the added entity.
	 */
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(
				Family.all(
						BodyComponent.class,
						MovableComponent.class,
						PlayerComponent.class,
						SpriteComponent.class,
						LightComponent.class)
						.get());
	}

	/**
	 * Updates all components of the player that are modified by player input.
	 * Inputs are scaled to delta time if applicable.
	 *
	 * @param deltaTime the time since the last update.
	 */
	@Override
	public void update(float deltaTime) {

		for(Entity e: entities) {

			InputHandler inputHandler = e.getComponent(PlayerComponent.class).input;
			Body body = e.getComponent(BodyComponent.class).body;
			Light light = e.getComponent(LightComponent.class).light;
			Sprite sprite = e.getComponent(SpriteComponent.class).sprite;
			Vector2 velocity = e.getComponent(MovableComponent.class).velocity;

			velocity.set(inputHandler.getVelocity());

			// Set the position of the body and the light
			Vector2 position = body.getPosition();
			float radius = body.getFixtureList().first().getShape().getRadius();

			light.setPosition(position.x + radius, position.y + radius);

			// Gets the angle of the character relative to the current position and the
			float angle = inputHandler.getAngle(new Vector2(sprite.getX() + radius, sprite.getY() + radius));

			// Set rotation of the sprite and the light
			sprite.setRotation(angle);
			light.setDirection(angle);

			// If the player is shooting shoot
			if (inputHandler.isShooting())
				System.out.println(true);
		}
	}
}
