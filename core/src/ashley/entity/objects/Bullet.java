package ashley.entity.objects;

import ashley.components.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.world.BodyGenerator;
import com.mygdx.game.managers.world.PhysicsManager;

/**
 * @author Jerry Springer
 * @version 02 11 2019
 */
public class Bullet extends Entity {

	private static final String BULLET_JSON = "core/assets/BodyDefinitions/Bullet.json";

	public Bullet(final World world, final double angle) {
		super();

		Body body = BodyGenerator.bodyGenerator(Gdx.files.internal(BULLET_JSON), world);

		add(new BodyComponent(body));
		add(new MonsterComponent());
		add(new MovableComponent());
		add(new RenderableComponent());

		int width = (int) (2 *  body.getFixtureList().first().getShape().getRadius() * PhysicsManager.METERS_TO_PIXELS);

		Pixmap pixmap = new Pixmap(width, width, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.YELLOW);
		pixmap.fill();

		Sprite sprite = new Sprite(new Texture(pixmap));
		sprite.setRotation((float) angle);

		add(new SpriteComponent(sprite));

		pixmap.dispose();
	}
}
