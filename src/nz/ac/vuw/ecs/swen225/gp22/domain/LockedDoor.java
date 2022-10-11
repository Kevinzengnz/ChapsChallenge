package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

import java.util.NoSuchElementException;

/**
 * @author Alicia Robinson - 300560663
 */
public class LockedDoor extends Door {
    private final Colours colour;
    protected LockedDoor(Point point, String colourString) {
        super(point);
        if(colourString.isEmpty()){
            throw new IllegalArgumentException("Locked Door colour is null");
        }
        colour = getColour(colourString);
        this.setSprite(colour.door);
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal LockedDoor Point");
        }
        try {
            Key key = player.keys.stream()
                    .filter(k -> k.getColour().equals(this.colour.getName()))
                    .findFirst()
                    .get();
            model.remove(this);
            player.keys.remove(key);
            assert !player.keys.contains(key);
            assert !model.entities().contains(this);
        } catch(NoSuchElementException e){
            player.moveValid = false;
        }
    }

    public String getColour(){
        return this.colour.getName();
    }
    @Override
    public String toString() {return "Door_"+getColour();}
}

