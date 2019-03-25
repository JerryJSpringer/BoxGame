package ashley.systems;

import ashley.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The system that is used to render all sprites.
 *
 * @author Jerry Springer
 * @version 03 20 2019
 */
public class RenderSystem extends EntitySystem {

	/** The array that contains all corresponding entities. */
    private ImmutableArray<Entity> entities;

    /** The sprite batch that will be drawn to. */
    private SpriteBatch batch;

	/**
	 * Creates a new rendering system.
	 *
	 * @param batch the batch that will be drawn to.
	 */
	public RenderSystem(SpriteBatch batch) {
        super();
        this.batch = batch;
    }

	/**
	 * Called when a corresponding entity is added to the engine.
	 *
	 * @param engine the engine that contains the added entity.
	 */
	@Override
    public void addedToEngine(Engine engine) {

        entities = engine.getEntitiesFor(
                Family.all(
                        RenderableComponent.class,
                        SpriteComponent.class)
                        .get());
    }

	/**
	 * Renders the sprites to the screen.
	 *
	 * @param deltaTime the time since the last update.
	 */
	@Override
    public void update(float deltaTime) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
                GL20.GL_DEPTH_BUFFER_BIT |
                (Gdx.graphics.getBufferFormat()
                        .coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        batch.begin();

        for(Entity e: entities) {

            Sprite sprite = e.getComponent(SpriteComponent.class).sprite;

            sprite.draw(batch);
        }

        batch.end();
    }
}
