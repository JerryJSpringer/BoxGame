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
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.InputHandler;

/**
 * @author Jerry Springer
 * @version 02 09 2019
 */
public class PlayerInputSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;
	private World world;

	public PlayerInputSystem(final World world) {
		super();

		this.world = world;
	}

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

	@Override
	public void update(float deltaTime) {

		for(Entity e: entities) {

			InputHandler inputHandler = e.getComponent(PlayerComponent.class).input;
			Body body = e.getComponent(BodyComponent.class).body;
			Light light = e.getComponent(LightComponent.class).light;
			Sprite sprite = e.getComponent(SpriteComponent.class).sprite;
			Vector2 velocity = e.getComponent(MovableComponent.class).velocity;

			velocity.set(inputHandler.getVelocity());

			// Gets the angle of the character relative to the current position and the
			float angle = inputHandler.getAngle(new Vector2(sprite.getX(), sprite.getY()));

			// Set rotation of the sprite and the light
			sprite.setRotation(angle);
			light.setDirection(angle);

			// Set the position of the body and the light
			Vector2 position = body.getPosition();
			float radius = body.getFixtureList().first().getShape().getRadius();

			light.setPosition(position.x + radius, position.y + radius);

			// If the player is shooting shoot
			if (inputHandler.isShooting())
				System.out.println(true);
		}
	}
}
