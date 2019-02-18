package com.mygdx.game.screens;

import ashley.entity.actors.Character;
import ashley.entity.actors.Monster;
import ashley.entity.level.Wall;
import ashley.systems.MovementSystem;
import ashley.systems.PlayerInputSystem;
import ashley.systems.RenderSystem;
import ashley.systems.SpriteUpdateSystem;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.managers.CollisionManager;
import com.mygdx.game.managers.InputHandler;
import com.mygdx.game.managers.PhysicsManager;

/**
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

    public GameScreen(final MyGdxGame game, final InputHandler inputHandler) {

        this.game = game;

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, PhysicsManager.WORLD_SIZE, PhysicsManager.WORLD_SIZE);
        viewport = new FitViewport(PhysicsManager.WORLD_SIZE, PhysicsManager.WORLD_SIZE, camera);
		camera.position.set(PhysicsManager.WORLD_SIZE / 2, PhysicsManager.WORLD_SIZE / 2, 0);
        game.batch.setProjectionMatrix(camera.combined);
        inputHandler.setViewport(viewport);

		debugRenderer = new Box2DDebugRenderer();
		debugMatrix = new Matrix4(camera.combined);

        // Engine
        engine = new Engine();

        engine.addSystem(new PlayerInputSystem(world));
		engine.addSystem(new RenderSystem(game.batch));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new SpriteUpdateSystem());

        // World
        world = new World(new Vector2(), false);
        world.setContactListener(new CollisionManager());


		// Lighting and Raycast Handler
		rayHandler = new RayHandler(world);
		rayHandler.useCustomViewport(viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		rayHandler.setAmbientLight(0.01f, 0.01f, 0.05f, 1f);
		rayHandler.setBlurNum(2);
		rayHandler.setShadows(true);
		rayHandler.setCulling(true);
		//rayHandler.setLightShader(Gaussian.createBlurShader((int) camera.viewportWidth, (int) camera.viewportHeight));

		PointLight p1 = new PointLight(rayHandler, 75, Color.WHITE, 20, 26, 26);
		p1.setSoft(true);
		p1.setStaticLight(false);

        // Entities
		//engine.addEntity(new Background());
		engine.addEntity(new Wall(world));
        engine.addEntity(new Character(world, rayHandler, inputHandler));
        engine.addEntity(new Monster(world));
    }

    @Override
    public void render(float delta) {

    	viewport.apply();

        engine.update(1/60f);
        world.step(1/60f, 6, 2);

        rayHandler.setCombinedMatrix(camera);
		rayHandler.updateAndRender();

        debugRenderer.render(world, debugMatrix);
    }

    @Override
    public void resize(int width, int height) {
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
