package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.stream.Stream;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
/**
 * Methods needed by all Entities in the game.
 * An Entity includes anything shown in the game.
 *
 * @author Alicia Robinson - 300560663
 */

public interface Entity {

    /**
     * Allows Entities states to be changed depending on what is happening in game.
     *
     * @param m model of game
     */
    default void ping(Model m){}

    /**
     * Gets sprite name.
     *
     * @return Entities sprite
     */
    String getSpriteName();

    /**
     * Gets point.
     *
     * @return Entities current point
     */
    Point getPoint();

    /**
     * Gets depth.
     *
     * @return depth of Entity for display purposes
     */
    int getDepth();

    /**
     * Allows Player to interact with other Entities
     * @param model model of game
     * @param player Player object
     * @param point point that player wants to move to
     */
    default void doAction(Model model, Player player, Point point){}

    /**
     * Checks if robot can move to given point depending on the entity.
     *
     * @param model model of game
     * @param robot robot object
     * @param point point that robot wants to move to
     */
    default void checkRobotMove(Model model, Robot robot, Point point){}

    /**
     * Sets the Entities sound effect.
     *
     * @param soundEffect runnable sound effect for when Entity is interacted with.
     */
    default void setSoundEffect(Runnable soundEffect){}

    /**
     * Gets colour of Entity.
     *
     * @param colourString string of the colour wanted
     * @return colour object matching the given colour string
     */
    default Colours getColour(String colourString) {
        return Stream.of(Colours.values())
                .filter(e -> e.toString().equals(colourString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown colour '"
                        + colourString + "' for enum Colours"));
    }


    /**
     * Holds Door and Key sprite that goes with each colour.
     */
    enum Colours {
        YELLOW("DOOR_YELLOW", "KEY_YELLOW", "YELLOW"),
        GREEN("DOOR_GREEN", "KEY_GREEN", "GREEN"),
        BLUE("DOOR_BLUE", "KEY_BLUE", "BLUE"),
        RED("DOOR_RED", "KEY_RED", "RED");

        /**
         * Key sprite name.
         */
        final String key;
        /**
         * Door sprite name.
         */
        final String door;
        /**
         * Colour name.
         */
        final String name;

        /**
         * Creates new colour with given name, door sprite and key sprite.
         *
         * @param door door sprite name
         * @param key key sprite name
         * @param name colour name
         */
        Colours(String door, String key, String name) {
            this.key = key;
            this.door = door;
            this.name = name;
        }

        /**
         * Gets name of colour.
         *
         * @return name of the colour.
         */
        String getName() {
            return this.name;
        }
    }
}
