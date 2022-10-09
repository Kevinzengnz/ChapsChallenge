package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

import java.util.NoSuchElementException;

/**
 * @author Alicia Robinson - 300560663
 */
public class LockedDoor extends Door{
    Colours colour;
    public LockedDoor(Point point, String colourString) {
        super(point);
        colour = getColour(colourString);
        //TODO check for null colour
        this.sprite = colour.door;
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        //TODO check that door point and player point are equal
        try {
            Key key = player.keys.stream()
                    .filter(k -> k.getColour().equals(this.colour.getName()))
                    .findFirst()
                    .get();
            model.remove(this);
            player.keys.remove(key);
        } catch(NoSuchElementException e){
            player.moveValid = false;
        }
    }

    public String getColour(){
        return this.colour.getName();
    }
    public String toString() {return "Door_"+getColour();}
}

