package ashley.systems;

import ashley.components.BodyComponent;
import ashley.components.MovableComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.managers.world.PhysicsManager;

/**
 * The movement system for all movable bodies.
 *
 * @author Jerry Springer
 * @version 03 20 2019
 */
public class MovementSystem extends EntitySystem {

    /** The array of entities. */
    private ImmutableArray<Entity> entities;

    /**
     * Creates a new movement system.
     */
    public MovementSystem() {
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
                        MovableComponent.class)
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
            MovableComponent movable = e.getComponent(MovableComponent.class);

			Vector2 velocity = movable.direction.setLength(movable.speed);
            body.setLinearVelocity(velocity.scl((float) (deltaTime / PhysicsManager.DELTA_CONSTANT)));
        }
    }
}
