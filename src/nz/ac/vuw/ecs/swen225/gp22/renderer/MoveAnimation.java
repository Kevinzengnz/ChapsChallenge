package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Class to represent an animation that performs a move from one tile to another.
 *
 * @author Oliver Silk 300564261
 */
public class MoveAnimation extends Animation {
  /**
   * The direction the animation is moving.
   */
  protected Direction direction;

  /**
   * The number of pings the animation has been running for.
   */
  protected int count;

  /**
   * Creates a new move Animation.
   *
   * @param startTile the tile to start the animation on
   * @param direction the tile to move the entity to
   * @param length    the length of the animation in ticks
   * @param entity    the entity to move
   */
  public MoveAnimation(Point startTile, Direction direction, int length, Entity entity) {
    super(startTile, length, entity);
    this.direction = direction;
    this.posX = tile.x() * tileSize;
    this.posY = tile.y() * tileSize;
    this.count = length;
  }

  /**
   * Alternative constructor that has start and end tiles rather than a direction.
   *
   * @param startTile the tile to start the animation in
   * @param endTile   the tile to end the animation in
   * @param length    the length of the animation in ticks
   * @param entity    the entity to animate
   */
  public MoveAnimation(Point startTile, Point endTile, int length, Entity entity) {
    super(startTile, length, entity);
    if (startTile.x() > endTile.x()) {
      direction = Direction.Left;
    } else if (startTile.x() < endTile.x()) {
      direction = Direction.Right;
    } else if (startTile.y() > endTile.y()) {
      direction = Direction.Up;
    } else if (startTile.y() < endTile.y()) {
      direction = Direction.Down;
    }
  }

  /**
   * Gets whether the animation has finished.
   *
   * @return whether the animation has finished
   */
  @Override
  public boolean isFinished() {
    return count <= 0;
  }

  /**
   * Causes the animation to advance 1 tick and move in the direction set.
   */
  @Override
  public void ping() {
    if (isFinished()) {
      return;
    }
    double moveAmount = (double) tileSize / length;

    switch (direction) {
      case Right -> posX += moveAmount;
      case Left -> posX -= moveAmount;
      case Up -> posY -= moveAmount;
      case Down -> posY += moveAmount;
      case default -> throw new IllegalStateException("MoveAnimation must have valid direction");
    }
    count--;
  }

  /**
   * Creates a copy of the animation.

   * @return a new copy of the MoveAnimation
   */
  @Override
  public Animation copy() {
    return new MoveAnimation(tile, direction, length, entity);
  }
}
