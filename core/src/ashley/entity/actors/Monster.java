package ashley.entity.actors;

import ashley.components.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.BodyGenerator;

/**
 * The monster class.
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class Monster extends Entity {

	/** The JSON file that describes the monster. */
	private static final String MONSTER_JSON = "core/assets/BodyDefinitions/Monster.json";

	/**
	 * Creates a new monster.
	 *
	 * @param world the physics world that the monster is created in.
	 */
	public Monster(final World world) {
		super();

		// Creating the body
		Body body = BodyGenerator.bodyGenerator(Gdx.files.internal(MONSTER_JSON), world);


		// Sprites
		int width = (int) (2 *  body.getFixtureList().first().getShape().getRadius());

		Pixmap pixmap = new Pixmap(width, width, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();

		Sprite sprite = new Sprite(new Texture(pixmap));

		// Adding the component
		add(new BodyComponent(body));
		add(new MonsterComponent());
		add(new MovableComponent());
		add(new RenderableComponent());
		add(new SpriteComponent(sprite));

		// Dispose
		pixmap.dispose();
	}
}
