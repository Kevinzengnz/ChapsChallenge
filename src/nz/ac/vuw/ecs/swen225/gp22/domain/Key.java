package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;

import java.util.Objects;
/**
 * @author Alicia Robinson - 300560663
 */
public class Key extends Collectable{
    Colours colour;
    public Key(String colourString, Point point) {
        super(point);
        colour = getColour(colourString.toUpperCase());
        this.sprite = colour.key;
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        player.addKey(this);
        model.remove(this);
        assert player.keys.contains(this);
        assert !model.entities().contains(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return colour == key.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour);
    }

    public String getColour(){
        return this.colour.getName();
    }
}