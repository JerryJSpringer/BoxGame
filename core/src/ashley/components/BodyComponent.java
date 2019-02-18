package ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * @author Jerry Springer
 * @version 02 09 2019
 */
public class BodyComponent implements Component {

    public Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }
}
