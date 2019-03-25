package ashley.entity.level;

import ashley.components.RenderableComponent;
import ashley.components.SpriteComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.managers.world.PhysicsManager;

/**
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class Background extends Entity {

	public Background() {
		super();

		// Sprites
		Pixmap pixmap = new Pixmap(PhysicsManager.WORLD_SIZE, PhysicsManager.WORLD_SIZE, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.BLACK);
		pixmap.fill();

		Sprite sprite = new Sprite(new Texture(pixmap));

		// Add the components
		add(new RenderableComponent());
		add(new SpriteComponent(sprite));

		// Dispose
		pixmap.dispose();
	}
}
