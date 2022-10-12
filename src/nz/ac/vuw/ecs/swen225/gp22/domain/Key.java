package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;

import java.util.Objects;

/**
 * Represents Keys in the game.
 * Keys are able to unlock LockedDoors
 * @author Alicia Robinson - 300560663
 */
public class Key extends Collectable{
    /**
     * Colour of the key
     */
    private final Colours colour;

    /**
     * Creates Key from given point and colourString
     * @param point position of key
     * @param colourString colour of key
     */
    protected Key(Point point, String colourString) {
        super(point);
        if(colourString.isEmpty()){
            throw new IllegalArgumentException("Key colour is null");
        }
        colour = getColour(colourString);
        this.setSprite(colour.key);
    }
    /**
     * @return colour of key as a string
     */
    public String getColour(){
        return this.colour.getName();
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal Key Point");
        }
        soundEffect.run();
        player.addKey(this);
        model.remove(this);
        this.setPoint(new Point(0, 0));
        assert player.keys.contains(this);
        assert !model.entities().contains(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return colour == key.colour && this.getPoint().equals(((Key) o).getPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour);
    }

}