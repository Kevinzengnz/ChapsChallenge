package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Sprite {
    FLOOR("Assets/Tiles/floor.png"),
    INFO("Assets/Tiles/info.png"),
    WALL("Assets/Tiles/wall.png"),
    EXIT("Assets/Tiles/exit.png"),

    PLAYER_UP("Assets/Actors/player_up.png"),
    PLAYER_DOWN("Assets/Actors/player_down.png"),
    PLAYER_LEFT("Assets/Actors/player_left.png"),
    PLAYER_RIGHT("Assets/Actors/player_right.png"),
    PLAYER_WALK_UP_1("Assets/Actors/player_up1.png"),
    PLAYER_WALK_UP_2("Assets/Actors/player_up2.png"),
    PLAYER_WALK_DOWN_1("Assets/Actors/player_down1.png"),
    PLAYER_WALK_DOWN_2("Assets/Actors/player_down2.png"),
    PLAYER_WALK_LEFT_1("Assets/Actors/player_left1.png"),
    PLAYER_WALK_LEFT_2("Assets/Actors/player_left2.png"),
    PLAYER_WALK_RIGHT_1("Assets/Actors/player_right1.png"),
    PLAYER_WALK_RIGHT_2("Assets/Actors/player_right2.png"),

    DOOR_BLUE("Assets/Tiles/blue_door.png"),
    DOOR_RED("Assets/Tiles/red_door.png"),
    DOOR_YELLOW("Assets/Tiles/yellow_door.png"),
    DOOR_GREEN("Assets/Tiles/green_door.png"),
    DOOR_EXIT("Assets/Tiles/exit_door.png"),

    KEY_BLUE("Assets/Items/blue_key.png"),
    KEY_RED("Assets/Items/red_key.png"),
    KEY_YELLOW("Assets/Items/yellow_key.png"),
    KEY_GREEN("Assets/Items/green_key.png"),

    COIN("Assets/Items/coin.png"),

    UI_TWO("Assets/UI/2.png");

    public final BufferedImage image;
    Sprite(String filename) {
        try{image = ImageIO.read(new File(filename));}
        catch(IOException e) { throw new Error(e); }
    }
}
