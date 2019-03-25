package ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * A component that indicates a movable object in the game world.
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class MovableComponent implements Component {

    public Vector2 direction;
    public float speed;

    public MovableComponent() {

        this(0);
    }

    public MovableComponent(final int speed) {

        this(speed, new Vector2());
    }

    public MovableComponent(final int speed, final Vector2 direction) {

        this.direction = new Vector2(direction);
        this.speed = speed;
    }
}
