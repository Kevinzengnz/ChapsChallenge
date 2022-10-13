package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents a FloorTile in the game.
 * Actors can freely move over all FloorTiles.
 *
 * @author Alicia Robinson - 300560663
 */
public class FloorTile extends Tile {
  /**
   * Creates a FloorTile at given position.
   *
   * @param point FloorTile position
   */
  protected FloorTile(Point point) {
    super("FLOOR", point);
  }
}
