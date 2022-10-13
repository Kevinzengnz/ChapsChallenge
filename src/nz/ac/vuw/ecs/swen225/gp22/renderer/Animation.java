package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Abstract superclass for all Animations.
 *
 * @author Oliver Silk 300564261
 */
public abstract class Animation {
  /**
   * The tile the animation is on.
   */
  Point tile;
  /**
   * The x coordinate of the animation in pixels. Not using Point as needs double.
   */
  protected double posX;

  /**
   * The y coordinate of the animation in pixels. Not using Point as needs double.
   */
  protected double posY;
  /**
   * The entity that is being animated.
   */
  Entity entity;
  /**
   * The length of the animation.
   */
  int length;
  /**
   * The size in pixels of the tiles.
   */
  static final int tileSize = 64;

  /**
   * Constructs a new animation.

   * @param tile the tile the animation is on.
   * @param length the length of the animation.
   * @param entity the entity to animate.
   */
  public Animation(Point tile, int length, Entity entity) {
    this.tile = tile;
    this.posX = tile.x() * tileSize;
    this.posY = tile.y() * tileSize;
    this.length = length;
    this.entity = checkValid(entity);
  }

  /**
   * Checks if the entity provided is valid.

   * @param entity the entity to check
   * @return the entity if it is valid
   */
  private Entity checkValid(Entity entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Entity cannot be null");
    }
    return entity;
  }


  /**
   * Gets the x coordinate in screen space.
   *
   * @return Integer of x coordinate in screen space
   */
  int getX() {
    return (int) posX;
  }

  /**
   * Gets the y coordinate in screen space.
   *
   * @return Integer of y coordinate in screen space
   */
  int getY() {
    return (int) posY;
  }

  /**
   * Gets the sprite of the animation. By default, returns the sprite of the entity animated.
   *
   * @return the Sprite
   */
  Sprite getSprite() {
    return Sprite.valueOf(entity.getSpriteName());
  }

  /**
   * Gets if the animation has finished running.
   *
   * @return boolean whether the animation has finished
   */
  abstract boolean isFinished();

  /**
   * Gets the entity that is being animated.
   *
   * @return the entity being animated
   */
  Entity getEntity() {
    return entity;
  }

  /**
   * Updates the animation.
   */
  abstract void ping();

  /**
   * Makes a copy of the animation.
   *
   * @return a copy of the animation
   */
  abstract Animation copy();
}
