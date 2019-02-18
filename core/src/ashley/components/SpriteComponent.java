package ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class SpriteComponent implements Component {

    public Sprite sprite;

    public SpriteComponent(Sprite sprite) {

        this.sprite = sprite;
    }
}
