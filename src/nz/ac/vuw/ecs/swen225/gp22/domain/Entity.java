package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.awt.*;
import java.util.stream.Stream;
/**
 * @author Alicia Robinson - 300560663
 */
public interface Entity {
    default void ping(Model m){

    }
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
        YELLOW(Sprite.DOOR_YELLOW, Sprite.KEY_YELLOW, "yellow"),
        GREEN(Sprite.DOOR_GREEN, Sprite.KEY_GREEN, "green"),
        BLUE(Sprite.KEY_BLUE, Sprite.KEY_BLUE, "blue"),
        RED(Sprite.DOOR_RED, Sprite.KEY_RED, "red");

        final Sprite key;
        final Sprite door;
        final String name;

        Colours(Sprite door, Sprite key, String name){
            this.key = key;
            this.door = door;
            this.name = name;
        }
        String getName(){
            return this.name;
        }
    }
}
