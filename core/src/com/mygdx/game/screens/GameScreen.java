package com.mygdx.game.screens;

import ashley.entity.actors.Character;
import ashley.entity.actors.Monster;
import ashley.entity.level.Wall;
import ashley.systems.*;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.managers.world.CollisionManager;
import com.mygdx.game.managers.input.GameInputAdapter;
import com.mygdx.game.managers.world.PhysicsManager;

/**
 * The main game screen.
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class GameScreen implements Screen {

    private final MyGdxGame game;

    private Engine engine;
    private World world;

    private OrthographicCamera camera;
	private Viewport viewport;
	private Box2DDebugRenderer debugRenderer;
	private Matrix4 debugMatrix;

	private RayHandler rayHandler;

    public GameScreen(final MyGdxGame game) {

        this.game = game;

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, PhysicsManager.WORLD_SIZE, PhysicsManager.WORLD_SIZE);
        viewport = new FitViewport(PhysicsManager.WORLD_SIZE, PhysicsManager.WORLD_SIZE, camera);
		camera.position.set(PhysicsManager.WORLD_SIZE / 2, PhysicsManager.WORLD_SIZE / 2, 0);
        game.batch.setProjectionMatrix(camera.combined);

		debugRenderer = new Box2DDebugRenderer();
		debugMatrix = new Matrix4(camera.combined);


        // Engine
        engine = new Engine();

		// Input Processing
        InputHandlerSystem inputHandlerSystem = new InputHandlerSystem(viewport);
		Gdx.input.setInputProcessor(new GameInputAdapter(0, inputHandlerSystem));

		// Add Systems to engine
		engine.addSystem(new RenderSystem(game.batch));
		engine.addSystem(inputHandlerSystem);
        engine.addSystem(new MovementSystem());
        engine.addSystem(new SpriteUpdateSystem());
        engine.addSystem(new LightUpdateSystem());


        // World
        world = new World(new Vector2(), false);
        world.setContactListener(new CollisionManager());

		// Lighting and Raycast Handler
		rayHandler = new RayHandler(world);
		rayHandler.useCustomViewport(viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		rayHandler.setAmbientLight(0.01f, 0.01f, 0.05f, 1f);
		rayHandler.setBlurNum(2);
		rayHandler.setCulling(true);
		rayHandler.setShadows(false);

        // Entities
		Character character = new Character(0, world, rayHandler);
		engine.addEntity(character);

		engine.addEntity(new Wall(world));
        engine.addEntity(new Monster(world));
    }

    @Override
    public void render(final float delta) {

    	viewport.apply();

        engine.update(delta);
        world.step(delta, 6, 2);

        rayHandler.setCombinedMatrix(camera);
		rayHandler.updateAndRender();

        // debugRenderer.render(world, debugMatrix);
    }

    @Override
    public void resize(final int width, final int height) {
    	viewport.update(width, height);
    	camera.position.set(PhysicsManager.WORLD_SIZE / 2, PhysicsManager.WORLD_SIZE / 2, 0);

		rayHandler.useCustomViewport(viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    	rayHandler.dispose();
    	world.dispose();
    	game.batch.dispose();
    }
}
