package nz.ac.vuw.ecs.swen225.gp22.domain;

public class EntityFactory {
    public Entity createEntity(String type, Point point){
        switch(type){
            case "PLAYER_UP":
                return new Player(point,"PLAYER_UP");
            case "PLAYER_DOWN":
                return new Player(point,"PLAYER_DOWN");
            case "PLAYER_LEFT":
                return new Player(point,"PLAYER_LEFT");
            case "PLAYER_RIGHT":
                return new Player(point,"PLAYER_RIGHT");
            case "WALL":
                return new WallTile(point);
            case "FLOOR":
                return new FloorTile(point);
            case "INFO":
                return new InfoTile(point);
            case "KEY_YELLOW":
                return new Key(point, "YELLOW");
            case "KEY_GREEN":
                return new Key(point, "GREEN");
            case "KEY_BLUE":
                return new Key(point, "BLUE");
            case "KEY_RED":
                return new Key(point, "RED");
            case "DOOR_YELLOW":
                return new LockedDoor(point, "YELLOW");
            case "DOOR_GREEN":
                return new LockedDoor(point, "GREEN");
            case "DOOR_BLUE":
                return new LockedDoor(point, "BLUE");
            case "DOOR_RED":
                return new LockedDoor(point, "RED");
            case "TREASURE":
                return new Treasure(point);
            case "DOOR_EXIT":
                return new ExitDoor(point);
            case "EXIT":
                return new Exit(point);
        }
        throw new IllegalArgumentException("Invalid Entity Type");
    }
}
