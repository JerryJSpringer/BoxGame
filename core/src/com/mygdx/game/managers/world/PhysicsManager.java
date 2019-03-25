package com.mygdx.game.managers.world;

/**
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class PhysicsManager {

	public static final double METERS_TO_PIXELS = 13;
    public static final double PIXELS_TO_METERS = 1/METERS_TO_PIXELS;

	public static final int WORLD_SIZE = 52;
	public static final double SCREEN_SIZE = 52 * METERS_TO_PIXELS;

	public static final double DELTA_CONSTANT = 1/60f;

    public static final short PLAYER_GROUP = -1;
    public static final short ENEMY_GROUP = -2;
    public static final short LEVEL_GROUP = -3;
    public static final short LIGHT_GROUP = -4;

    private PhysicsManager() {
    }
}
