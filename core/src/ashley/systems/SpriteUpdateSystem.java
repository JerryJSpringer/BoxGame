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
 * @author Jerry Springer
 * @project Autumn 2018
 */
public class SpriteUpdateSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;

	public SpriteUpdateSystem() {

		super();
	}

	@Override
	public void addedToEngine(Engine engine) {

		entities = engine.getEntitiesFor(
				Family.all(
						BodyComponent.class,
						RenderableComponent.class,
						SpriteComponent.class)
						.get());
	}

	@Override
	public void update(float deltaTime) {

		for(Entity e: entities) {

			Body body = e.getComponent(BodyComponent.class).body;
			Vector2 position = body.getPosition();

			Sprite sprite = e.getComponent(SpriteComponent.class).sprite;

			sprite.setPosition(position.x, position.y);
		}
	}
}
