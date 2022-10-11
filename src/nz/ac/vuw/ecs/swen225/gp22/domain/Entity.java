package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.util.stream.Stream;
/**
 * @author Alicia Robinson - 300560663
 */
public interface Entity {
    default void ping(Model m){}
    String getSprite();

    Point getPoint();

    int getDepth();
    default void doAction(Model model, Player player, Point point){}

    default Colours getColour(String colourString){
        return Stream.of(Colours.values())
                .filter(e -> e.toString().equals(colourString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown colour '" +
                        colourString + "' for enum Colours"));
    }

    enum Colours{
        YELLOW("DOOR_YELLOW", "KEY_YELLOW", "YELLOW"),
        GREEN("DOOR_GREEN", "KEY_GREEN", "GREEN"),
        BLUE("KEY_BLUE", "KEY_BLUE", "BLUE"),
        RED("DOOR_RED", "KEY_RED", "RED");

        final String key;
        final String door;
        final String name;

        Colours(String door, String key, String name){
            this.key = key;
            this.door = door;
            this.name = name;
        }
        String getName(){
            return this.name;
        }
    }
}
