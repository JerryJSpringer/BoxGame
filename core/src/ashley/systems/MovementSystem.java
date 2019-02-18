package ashley.systems;

import ashley.components.BodyComponent;
import ashley.components.MovableComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * @author Jerry Springer
 * @project Autumn 2018
 */
public class MovementSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    public MovementSystem() {
        super();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        BodyComponent.class,
                        MovableComponent.class)
                        .get());
    }

    @Override
    public void update(float deltaTime) {

        for(Entity e: entities) {
            Body body = e.getComponent(BodyComponent.class).body;
            MovableComponent movable = e.getComponent(MovableComponent.class);

            body.setLinearVelocity(movable.velocity);
        }
    }
}
