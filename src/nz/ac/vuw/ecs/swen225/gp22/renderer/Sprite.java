package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Sprite {
    FLOOR("Tiles/floor.png"),
    WALL("Tiles/wall.png"),
    PLAYER("Actors/player.png");

    public final BufferedImage image;
    Sprite(String filename) {
        try{image = ImageIO.read(new File(filename));}
        catch(IOException e) { throw new Error(e); }
    }
}
