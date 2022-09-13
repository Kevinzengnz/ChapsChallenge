//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Key extends Collectable{
    Colours colour;
    public Key(String colourString, Point point) {
        super(point);
        colour = getColour(colourString.toUpperCase());
        this.sprite = colour.key;
    }
}