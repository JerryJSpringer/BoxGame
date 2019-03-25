package ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class SpriteComponent implements Component {

    public Sprite sprite;
    public float angle;

    public SpriteComponent(final Sprite sprite) {

        this.sprite = sprite;
        angle = 0;
    }

    public SpriteComponent(final Sprite sprite, final float angle) {

    	this.sprite = sprite;
    	this.angle = angle;
	}
}
