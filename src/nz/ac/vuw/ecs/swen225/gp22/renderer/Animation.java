package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Abstract superclass for all Animations
 * @author Oliver Silk
 * ID: 300564261
 */
abstract public class Animation {
    /**
     * The tile the animation is on.
     */
    Point tile;
    /**
     * The x and y coordinates of the animation in pixels. Not using Point as needs double.
     */
    double x, y;
    /**
     * The entity that is being animated.
     */
    Entity entity;
    /**
     * The length of the animation.
     */
    int length;
    /**
     * The size in pixels of the tiles
     */
    static final int tileSize = 64;

    public Animation(Point tile, int length, Entity entity) {
        this.tile = tile;
        this.x = tile.x() * tileSize;
        this.y = tile.y() * tileSize;
        this.length = length;
        this.entity = entity;
    }


    /**
     * Gets the x coordinate in screen space
     * @return Integer of x coordinate in screen space
     */
    int getX() {
        return (int) x;
    }

    /**
     * Gets the y coordinate in screen space
     * @return Integer of y coordinate in screen space
     */
    int getY() {
        return (int) y;
    }

    /**
     * Gets the sprite of the animation. By default, returns the sprite of the entity animated.
     * @return the Sprite
     */
    Sprite getSprite() {
        return Sprite.valueOf(entity.getSprite());
    }

    /**
     * Gets if the animation has finished running
     * @return boolean whether the animation has finished
     */
    abstract boolean isFinished();

    /**
     * Gets the entity that is being animated
     * @return the entity being animated
     */
    Entity getEntity() {
        return entity;
    }

    /**
     * Updates the animation
     */
    abstract void ping();

    /**
     * Makes a copy of the animation
     * @return a copy of the animation
     */
    abstract Animation copy();
}
