package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class ExitDoor extends Door {
    protected ExitDoor(Point point) {
        super(point);
        this.setSprite("DOOR_EXIT");
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal ExitDoor Point");
        }
        if(player.getTreasureCollected() != 5){
            player.moveValid = false;
        } else{
            if(soundEffect == null){
                throw new IllegalArgumentException("Sound Effect is Null");
            }
            soundEffect.run();
            model.remove(this);
            assert !model.entities().contains(this);
        }
    }
    @Override
    public String toString() {return this.getSprite();}

}
