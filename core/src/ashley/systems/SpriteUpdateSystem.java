package ashley.systems;

import ashley.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * The system that updates the sprites for their location in the physics world.
 *
 * @author Jerry Springer
 * @version Autumn 2018
 */
public class SpriteUpdateSystem extends EntitySystem {

	/** The array that contains the corresponding entities. */
	private ImmutableArray<Entity> entities;

	/**
	 * Creates a new sprite update system.
	 */
	public SpriteUpdateSystem() {
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
						RenderableComponent.class,
						SpriteComponent.class)
						.get());
	}

	/**
	 * Updates all sprites for their new position in the physics world.
	 *
	 * @param deltaTime the time since the last update.
	 */
	@Override
	public void update(float deltaTime) {

		for(Entity e: entities) {

			Body body = e.getComponent(BodyComponent.class).body;
			Vector2 position = body.getPosition();

			SpriteComponent spriteComponent = e.getComponent(SpriteComponent.class);

			Sprite sprite = spriteComponent.sprite;
			float angle = spriteComponent.angle;

			sprite.setPosition(position.x, position.y);
			sprite.setRotation(angle);
		}
	}
}
