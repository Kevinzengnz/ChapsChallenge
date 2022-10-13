package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents the Exit Door in the game.
 * Can be opened once all Treasures are collected.
 * @author Alicia Robinson - 300560663
 */
public class ExitDoor extends Door {
    /**
     * Creates ExitDoor at given point
     * @param point ExitDoor position
     */
    protected ExitDoor(Point point) {
        super(point);
        this.setSpriteName("DOOR_EXIT");
    }
    @Override
    public void doAction(Model model, Actor actor, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal ExitDoor Point");
        }
        if(model.treasuresLeft() != 0){
            actor.moveValid = false;
        } else{
            soundEffect.run();
            model.remove(this);
            assert !model.entities().contains(this);
        }
    }

}
