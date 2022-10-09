package nz.ac.vuw.ecs.swen225.gp22.domain;

public class EntityFactory {
    public Entity createEntity(String type, Point point){
        switch(type){
            case "Player":
                return new Player(point);
            case "WallTile":
                return new WallTile(point);
            case "FloorTile":
                return new FloorTile(point);
            case "InfoTile":
                return new InfoTile(point);
            case "Key_YELLOW":
                return new Key(point, "YELLOW");
            case "Key_GREEN":
                return new Key(point, "GREEN");
            case "Key_BLUE":
                return new Key(point, "BLUE");
            case "Key_RED":
                return new Key(point, "RED");
            case "Door_YELLOW":
                return new LockedDoor(point, "YELLOW");
            case "Door_GREEN":
                return new LockedDoor(point, "GREEN");
            case "Door_BLUE":
                return new LockedDoor(point, "BLUE");
            case "Door_RED":
                return new LockedDoor(point, "RED");
            case "Treasure":
                return new Treasure(point);
            case "ExitDoor":
                return new ExitDoor(point);
            case "Exit":
                return new Exit(point);
        }
        throw new IllegalArgumentException("Invalid Entity Type");
    }
}
