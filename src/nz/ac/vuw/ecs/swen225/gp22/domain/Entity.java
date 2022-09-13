package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.awt.*;
import java.util.stream.Stream;

public interface Entity {
    Sprite getSprite();

    Point getPoint();

    int getDepth();

    default Colours getColour(String colourString){
        return Stream.of(Colours.values())
                .filter(e -> e.toString().equals(colourString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown colour '" +
                        colourString + "' for enum Colours"));
    }

    enum Colours{
        YELLOW(Sprite.DOOR_YELLOW, Sprite.KEY_YELLOW),
        GREEN(Sprite.DOOR_GREEN, Sprite.KEY_GREEN),
        RED(Sprite.DOOR_RED, Sprite.KEY_RED);

        Sprite key;
        Sprite door;

        Colours(Sprite key, Sprite door){
            this.key = key;
            this.door = door;
        }
    }
}
