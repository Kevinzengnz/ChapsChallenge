package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.NoSuchElementException;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents a locked door.
 * LockedDoors can be opened with a key of the same colour.
 *
 * @author Alicia Robinson - 300560663
 */
public class LockedDoor extends Door {
    /**
     * Colour of door.
     */
    private final Colours colour;

    /**
     * Creates a LockedDoor Object with given point and colour.
     *
     * @param point position of LockedDoor
     * @param colourString colour of LockedDoor
     */
    protected LockedDoor(Point point, String colourString) {
        super(point);
        colour = getColour(colourString);
        this.setSpriteName(colour.door);
    }

    /**
     * Gets LockedDoor colour.
     *
     * @return colour of door
     */
    public String getColour() {
        return this.colour.getName();
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if (!this.getPoint().equals(point)) {
            throw new IllegalArgumentException("Player point does not equal LockedDoor Point");
        }
        try {
            Key key = player.keys.stream()
                    .filter(k -> k.getColour().equals(this.colour.getName()))
                    .findFirst()
                    .get();
            soundEffect.run();
            model.remove(this);
            player.removeKey(key);
            assert !player.keys.contains(key);
            assert !model.entities().contains(this);
        } catch (NoSuchElementException e) {
            player.moveValid = false;
        }
    }

}

