package ashley.systems;

import ashley.components.BodyComponent;
import ashley.components.LightComponent;
import ashley.components.MovableComponent;
import ashley.components.SpriteComponent;
import box2dLight.Light;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * @author Jerry Springer
 * @version 03 20 2019
 */
public class LightUpdateSystem extends EntitySystem {

	/** The array of entities. */
	private ImmutableArray<Entity> entities;

	/**
	 * Creates a new movement system.
	 */
	public LightUpdateSystem() {
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
						LightComponent.class)
						.get());
	}

	/**
	 * Updates all movable bodies according to their linear velocity scaled to delta time.
	 *
	 * @param deltaTime the time since the last engine update.
	 */
	@Override
	public void update(float deltaTime) {

		for(Entity e: entities) {
			Body body = e.getComponent(BodyComponent.class).body;
			Light light = e.getComponent(LightComponent.class).light;

			Vector2 position = body.getPosition();
			float radius = body.getFixtureList().first().getShape().getRadius();

			light.setPosition(position.x + radius, position.y + radius);

			SpriteComponent spriteComponent = e.getComponent(SpriteComponent.class);

			if (spriteComponent != null)
				light.setDirection(spriteComponent.angle);
		}
	}
}
