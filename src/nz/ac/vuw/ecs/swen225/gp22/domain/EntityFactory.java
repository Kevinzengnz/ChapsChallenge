package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Allows Entities to be created from external classes.
 *
 * @author Alicia Robinson - 300560663
 */
public class EntityFactory {
  /**
   * Creates new Entity of given type with given point.
   *
   * @param type  String of the type of Entity needing to be made
   * @param point Point that Entity needs to be created at
   * @return new Entity of given type
   */
  public Entity createEntity(String type, Point point) {
    if (point == null || type == null || type.isEmpty()) {
      throw new IllegalArgumentException("Type or Point are null");
    }
    return switch (type) {
      case "PLAYER_UP" -> new Player(point, "PLAYER_UP");
      case "PLAYER_DOWN" -> new Player(point, "PLAYER_DOWN");
      case "PLAYER_LEFT" -> new Player(point, "PLAYER_LEFT");
      case "PLAYER_RIGHT" -> new Player(point, "PLAYER_RIGHT");
      case "WALL" -> new WallTile(point);
      case "FLOOR" -> new FloorTile(point);
      case "INFO" -> new InfoTile(point);
      case "KEY_YELLOW" -> new Key(point, "YELLOW");
      case "KEY_GREEN" -> new Key(point, "GREEN");
      case "KEY_BLUE" -> new Key(point, "BLUE");
      case "KEY_RED" -> new Key(point, "RED");
      case "DOOR_YELLOW" -> new LockedDoor(point, "YELLOW");
      case "DOOR_GREEN" -> new LockedDoor(point, "GREEN");
      case "DOOR_BLUE" -> new LockedDoor(point, "BLUE");
      case "DOOR_RED" -> new LockedDoor(point, "RED");
      case "TREASURE" -> new Treasure(point);
      case "DOOR_EXIT" -> new ExitDoor(point);
      case "EXIT" -> new Exit(point);
      case "LAVA" -> new LavaTile(point);
      case "ROBOT_UP", "ROBOT_DOWN", "ROBOT_LEFT", "ROBOT_RIGHT" -> new Robot(point);
      default -> throw new IllegalArgumentException("Invalid Entity Type");
    };
  }
}
