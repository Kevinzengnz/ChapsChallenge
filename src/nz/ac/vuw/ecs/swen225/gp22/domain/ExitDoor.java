package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class ExitDoor extends Door{
    protected ExitDoor(Point point) {
        super(point);
        this.sprite = Sprite.DOOR_EXIT;
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        if(player.getTreasureCollected() != 5){
            player.moveValid = false;
        } else{
            model.remove(this);
        }
    }
    public String toString() {return "ExitDoor";}

}
