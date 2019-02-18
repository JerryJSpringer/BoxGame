package ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class MovableComponent implements Component {

    public Vector2 velocity;

    public MovableComponent() {

        velocity = new Vector2(0, 0);
    }

    public MovableComponent(float x, float y) {

        velocity = new Vector2(x, y);
    }

    public MovableComponent(Vector2 velocity) {

        this.velocity = velocity;
    }
}
