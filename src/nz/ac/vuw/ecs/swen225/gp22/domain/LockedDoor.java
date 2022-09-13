//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class LockedDoor extends Door{
    Colours colour;
    public LockedDoor(String colourString, Point point) {
        super(point);
        colour = getColour(colourString.toUpperCase());
        this.sprite = colour.door;
    }
}

