package ashley.entity.actors;

import ashley.components.*;
import box2dLight.ConeLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.BodyGenerator;
import com.mygdx.game.managers.InputHandler;

/**
 * The character class.
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class Character extends Entity {

	private static final String CHARACTER_JSON = "core/assets/BodyDefinitions/Character.json";
	private static final float CONE_DEGREE = 45;

    /**
     * Creates a new game character with components.
     */
    public Character(final World world, final RayHandler rayHandler, final InputHandler inputHandler) {
        super();

        // Create the body
		Body body = BodyGenerator.bodyGenerator(Gdx.files.internal(CHARACTER_JSON), world);

		// Creating the point light
		ConeLight light = new ConeLight(rayHandler, 50, Color.WHITE, 52,
				body.getPosition().x, body.getPosition().y, 0, CONE_DEGREE);
		light.setSoft(true);
		light.setSoftnessLength(3);

        // Sprites
		int width = (int) (2 *  body.getFixtureList().first().getShape().getRadius());

        Pixmap pixmap = new Pixmap(width, width, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();

        Sprite sprite = new Sprite(new Texture(pixmap));

        // Adding all components
		add(new BodyComponent(body));
		add(new PlayerComponent(inputHandler));
		add(new MovableComponent());
		add(new RenderableComponent());
		add(new LightComponent(light));
		add(new SpriteComponent(sprite));

		// Dispose
		pixmap.dispose();
    }
}
