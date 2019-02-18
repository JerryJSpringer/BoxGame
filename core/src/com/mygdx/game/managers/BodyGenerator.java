package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Used to build bodies from JSON files
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class BodyGenerator {

    private BodyGenerator() {
    }

    public static Body bodyGenerator(final FileHandle fileHandle,
                                     final World world) {

        Body body;

        String rawJson = fileHandle.readString();
        JsonReader jsonReader = new JsonReader();
        JsonValue file = jsonReader.parse(rawJson);

        BodyDef bodyDef = new BodyDef();
        JsonValue bodyDefInfo = file.get("BodyDef");

        String bodyType = bodyDefInfo.getString("type");
        if (bodyType.equalsIgnoreCase("DynamicBody")) {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        else if (bodyType.equalsIgnoreCase("StaticBody")) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }
        else if (bodyType.equalsIgnoreCase("KinematicBody")) {
            bodyDef.type = BodyDef.BodyType.KinematicBody;
        }
        else {
            Gdx.app.log("WARNING", "Box2D body type undefined");
        }

        if (bodyDefInfo.has("x") && bodyDefInfo.has("y")) {

            float x = (float) (bodyDefInfo.getFloat("x") * PhysicsManager.PIXELS_TO_METERS);
            float y = (float) (bodyDefInfo.getFloat("y") * PhysicsManager.PIXELS_TO_METERS);

            bodyDef.position.set(new Vector2(x, y));
        }
        body = world.createBody(bodyDef);


        JsonValue fixtures = file.get("Fixtures");
        for (JsonValue fixture: fixtures) {

            String fixtureType = fixture.getString("type");
            Shape shape;

            if (fixtureType.equalsIgnoreCase("BoxShape")) {
                shape = new PolygonShape();

                float sideLength = (fixture.getFloat("sideLength") / 2);
                ((PolygonShape) shape).setAsBox(sideLength, sideLength);
            }
            else if(fixtureType.equalsIgnoreCase("WallShape")) {
            	shape = new PolygonShape();

				float width = fixture.getFloat("width") / 2;
				float height = fixture.getFloat("height") / 2;
				float x = fixture.getFloat("x");
				float y = fixture.getFloat("y");

				((PolygonShape) shape).setAsBox(width, height, new Vector2(x, y), 0);
			}
            else if (fixtureType.equalsIgnoreCase("CircleShape")) {
                shape = new CircleShape();

                float radius = fixture.getFloat("radius");
                shape.setRadius(radius);
                ((CircleShape) shape).setPosition(new Vector2(radius, radius));
            }
            else {
                Gdx.app.log("WARNING", "Generated body shape was undefined");
                continue;
            }

            boolean isSensor = fixture.getBoolean("isSensor");

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.isSensor = isSensor;


            String group = fixture.getString("group");

            if (!group.equalsIgnoreCase("level")) {
				fixtureDef.density = fixture.getFloat("density");
				fixtureDef.friction = fixture.getFloat("friction");
			}

            if (group.equalsIgnoreCase("level")) {
                fixtureDef.filter.groupIndex = PhysicsManager.LEVEL_GROUP;
            }
            else if (group.equalsIgnoreCase("monster")) {
                fixtureDef.filter.groupIndex = PhysicsManager.ENEMY_GROUP;
            }
            else if (group.equalsIgnoreCase("player")) {
                fixtureDef.filter.groupIndex = PhysicsManager.PLAYER_GROUP;
            }
            else {
                Gdx.app.log("WARNING", "No collision group was identified.");
            }

            if (isSensor) {
            	fixtureDef.filter.groupIndex = 0;
            }

            body.createFixture(fixtureDef);
            body.setFixedRotation(true);

            shape.dispose();
        }


        return body;
    }
}
