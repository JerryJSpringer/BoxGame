package ashley.systems;

import ashley.components.BodyComponent;
import ashley.components.MovableComponent;
import ashley.components.PlayerComponent;
import ashley.components.SpriteComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.input.GameInputHandler;

import java.util.HashMap;

/**
 * Used to handle input from users. Currently works for only a single player,
 * but is built to potentially support multiple users in the future.
 *
 * @author Jerry Springer
 * @version 03 24 2019
 */
public class InputHandlerSystem extends EntitySystem implements GameInputHandler {

	/** The array of entities. */
	private ImmutableArray<Entity> entities;

	/** The map of players. */
	private HashMap<Integer, Entity> players;

	/** The amount of characters that exist. */
	private int playerCount;

	/** The viewport being used. */
	private Viewport viewport;

	/**
	 * Creates a new input handling system.
	 */
	public InputHandlerSystem(final Viewport viewport) {
		super();

		this.viewport = viewport;

		players = new HashMap<Integer, Entity>();
	}

	/**
	 * Called when a corresponding entity is added to the engine.
	 *
	 * @param engine the engine that contains the added entity.
	 */
	@Override
	public void addedToEngine(final Engine engine) {
		entities = engine.getEntitiesFor(
				Family.all(
						BodyComponent.class,
						MovableComponent.class,
						PlayerComponent.class,
						SpriteComponent.class)
						.get());
	}

	/**
	 * Updates all players according to their input system.
	 *
	 * @param deltaTime the time since the last engine update.
	 */
	@Override
	public void update(final float deltaTime) {

		if (playerCount != entities.size()) {
			for (Entity e: entities) {
				if (!players.containsValue(e))
					players.put(e.getComponent(PlayerComponent.class).playerId, e);
			}

			playerCount = entities.size();
		}

		for(Entity e: entities) {

			PlayerComponent playerComponent = e.getComponent(PlayerComponent.class);

			if (!Gdx.input.isTouched())
				look(playerComponent.playerId, Gdx.input.getX(), Gdx.input.getY());

		}
	}

	@Override
	public void move(final int playerId, final Vector2 direction) {

		Entity e = players.get(playerId);

		if (e == null)
			return;

		e.getComponent(MovableComponent.class).direction.set(direction);
	}

	@Override
	public void look(final int playerId, final int screenX, final int screenY) {

		Entity e = players.get(playerId);

		if (e == null)
			return;

		Vector2 position = e.getComponent(BodyComponent.class).body.getPosition();
		Vector2 screenPosition = viewport.project(position);
		Vector3 tp = new Vector3(screenX,
				(viewport.getScreenHeight() + viewport.getBottomGutterHeight() + viewport.getTopGutterHeight()) - screenY,
				0);

		e.getComponent(SpriteComponent.class).angle =
				(float) Math.toDegrees(Math.atan2(tp.y - screenPosition.y, tp.x - screenPosition.x));
	}
}
