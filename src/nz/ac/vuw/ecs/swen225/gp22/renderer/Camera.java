package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Class to represent the camera. Has support for animation.
 * @author Oliver Silk
 * ID: 300564261
 */
public class Camera {
    /**
     * The dimensions of the total size of the square vision area (width and height).
     */
    private final static int visionSize = 9;
    /**
     * The distance in tiles the camera can see (from camera location).
     */
    private final static int visionDistance = (visionSize-1)/2;

    /**
     * The size in pixels of the tiles
     */
    private final static int tileSize = 64;

    /**
     * The camera's position in tile coordinates (in world space)
     */
    private Point position;

    /**
     * The camera's animation. Used to move the camera smoothly between Tiles using MoveAnimation.
     */
    private Animation animation;

    /**
     * Creates a Camera at the location provided
     * @param position the position of the Camera
     */
    protected Camera(Point position) {
        this.position = position;
    }

    /**
     * Updates the camera to the position provided
     * @param position the new location of the Camera
     */
    public void update(Point position) {
        this.position = position;
        if (animation != null) animation.ping();
    }


    /**
     * Gets the x coordinate of the Camera in screen space. If the Camera has a valid animation it returns
     * the x coordinate from the Animation.
     * @return integer x coordinate of the Camera
     */
    public int getX() {
        if (animation != null && !animation.isFinished()) return animation.getX();
        return position.x()  * tileSize;
    }
    /**
     * Gets the y coordinate of the Camera in screen space. If the Camera has a valid animation it returns
     * the y coordinate from the Animation.
     * @return integer y coordinate of the Camera
     */
    public int getY() {
        if (animation != null && !animation.isFinished()) return animation.getY();
        return position.y() * tileSize;
    }

    /**
     * Gets the x coordinate of the Tile the camera is on (in world space)
     * @return integer of the x coordinate of the camera's tile
     */
    public int getTileX() {
        return position.x();
    }
    /**
     * Gets the y coordinate of the Tile the camera is on (in world space)
     * @return integer of the y coordinate of the camera's tile
     */
    public int getTileY() {
        return position.y();
    }

    /**
     * Gets the camera's position in tile coordinates (in world space)
     * @return tile coordinates as point
     */
    public Point getTilePoint() {
        return position;
    }

    /**
     * Adds an animation to the Camera. Replaces the existing animation if there is one present.
     * Used to smoothly animate the camera's position between tiles.
     * @param newAnimation the new animation to add to the camera
     */
    public void addAnimation(Animation newAnimation) {
        this.animation = newAnimation;
    }

    /**
     * Removes the camera's animation if one is present.
     */
    public void removeAnimation() {
        this.animation = null;
    }

    /**
     * @return the distance in tiles the camera can see (from camera location)
     */
    public int getVisionDistance() {
        return visionDistance;
    }

    /**
     * @return the dimensions of the total size of the square vision area (width and height)
     */
    public int getVisionSize() {
        return visionSize;
    }


}
