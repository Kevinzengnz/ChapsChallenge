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

    if(type.equalsIgnoreCase("PLAYER_UP")){
      return new Player(point, "PLAYER_UP");
    } else if (type.equalsIgnoreCase("PLAYER_DOWN")) {
      return new Player(point, "PLAYER_DOWN");
    } else if (type.equalsIgnoreCase("PLAYER_LEFT")) {
      return new Player(point, "PLAYER_LEFT");
    } else if (type.equalsIgnoreCase("PLAYER_RIGHT")) {
      return new Player(point, "PLAYER_RIGHT");
    } else if (type.equalsIgnoreCase("WALL")) {
      return new WallTile(point);
    } else if (type.equalsIgnoreCase("FLOOR")) {
      return new FloorTile(point);
    } else if (type.equalsIgnoreCase("INFO")) {
      return new InfoTile(point);
    } else if (type.equalsIgnoreCase("KEY_YELLOW")) {
      return new Key(point, "YELLOW");
    } else if (type.equalsIgnoreCase("KEY_GREEN")) {
      return new Key(point, "GREEN");
    } else if (type.equalsIgnoreCase("KEY_BLUE")) {
      return new Key(point, "BLUE");
    } else if (type.equalsIgnoreCase("KEY_RED")) {
      return new Key(point, "RED");
    } else if (type.equalsIgnoreCase("DOOR_YELLOW")) {
      return new LockedDoor(point, "YELLOW");
    } else if (type.equalsIgnoreCase("DOOR_GREEN")) {
      return new LockedDoor(point, "GREEN");
    } else if (type.equalsIgnoreCase("DOOR_BLUE")) {
      return new LockedDoor(point, "BLUE");
    } else if (type.equalsIgnoreCase("DOOR_RED")) {
      return new LockedDoor(point, "RED");
    } else if (type.equalsIgnoreCase("TREASURE")) {
      return new Treasure(point);
    } else if (type.equalsIgnoreCase("DOOR_EXIT")) {
      return new ExitDoor(point);
    } else if (type.equalsIgnoreCase("EXIT")) {
      return new Exit(point);
    } else if (type.equalsIgnoreCase("LAVA")) {
      return new LavaTile(point);
    } else if (type.equalsIgnoreCase("ROBOT_UP")) {
      return new Robot(point, "ROBOT_UP");
    } else if (type.equalsIgnoreCase("ROBOT_DOWN")) {
      return new Robot(point, "ROBOT_DOWN");
    } else if (type.equalsIgnoreCase("ROBOT_LEFT")) {
      return new Robot(point, "ROBOT_LEFT");
    } else if (type.equalsIgnoreCase("ROBOT_RIGHT")) {
      return new Robot(point, "ROBOT_RIGHT");
    } else{
      throw new IllegalArgumentException("Entity Type not Valid");
    }
  }
}
