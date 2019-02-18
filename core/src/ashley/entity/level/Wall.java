package ashley.entity.level;

import ashley.components.BodyComponent;
import ashley.components.RenderableComponent;
import ashley.components.SpriteComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.BodyGenerator;
import com.mygdx.game.managers.PhysicsManager;

/**
 * @author Jerry Springer
 * @version 02 10 2019
 */
public class Wall extends Entity {

	private static final String WALL_JSON = "core/assets/BodyDefinitions/Wall.json";

	public Wall(final World world) {
		super();

		// Creating the body
		Body body = BodyGenerator.bodyGenerator(Gdx.files.internal(WALL_JSON), world);

		// Sprites
		Pixmap pixmap = new Pixmap(PhysicsManager.WORLD_SIZE, PhysicsManager.WORLD_SIZE,
				Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GRAY);

		PolygonShape shape;
		Vector2 vertex = new Vector2();
		Vector2 vertex2 = new Vector2();
		int width;
		int height;
		int xTall = 0;
		int yTall = 0;
		int xWide = 0;
		int yWide = 0;

		// Creates the sprite in the proper location for each corner piece
		// Corner pieces are made up of two rectangles
		for (int i = 0; i < body.getFixtureList().size; i++) {

			shape = (PolygonShape) body.getFixtureList().get(i).getShape();

			// Gets the 0th and 2nd vertex of each shape
			shape.getVertex(0, vertex);
			shape.getVertex(2, vertex2);

			// Calculate the width and the height of the current block
			width = (int) ((vertex2.x - vertex.x));
			height = (int) ((vertex2.y - vertex.y));

			// If even create the "tall" pieces
			// If odd create the "wide" pieces
			if (i % 2 == 0) {

				pixmap.fillRectangle(xTall, yTall, width, height);

				// Move the position from the current piece to the next "tall" piece
				if (xTall == PhysicsManager.WORLD_SIZE - width) {
					xTall = 0;
					yTall += PhysicsManager.WORLD_SIZE - height;
				} else {
					xTall += PhysicsManager.WORLD_SIZE - width;
				}

			} else {

				pixmap.fillRectangle(xWide, yWide, width, height);

				// Move the position from the current piece to the next "wide" piece
				if (xWide == PhysicsManager.WORLD_SIZE - width) {
					xWide = 0;
					yWide += PhysicsManager.WORLD_SIZE - height;
				} else {
					xWide += PhysicsManager.WORLD_SIZE - width;
				}
			}
		}

		Sprite sprite = new Sprite(new Texture(pixmap));


		// Add the components
		add(new BodyComponent(body));
		add(new RenderableComponent());
		add(new SpriteComponent(sprite));

		// Dispose
		pixmap.dispose();
	}
}
