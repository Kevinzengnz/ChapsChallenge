package nz.ac.vuw.ecs.swen225.gp22.domain;
/**
 * @author Alicia Robinson - 300560663
 */
public class LockedDoor extends Door{
    Colours colour;
    public LockedDoor(String colourString, Point point) {
        super(point);
        colour = getColour(colourString.toUpperCase());
        this.sprite = colour.door;
    }

    public String getColour(){
        return this.colour.getName();
    }
}

