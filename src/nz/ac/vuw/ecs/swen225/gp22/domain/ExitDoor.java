package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class ExitDoor extends Door{
    public ExitDoor(Point point) {
        super(point);
        this.sprite = Sprite.DOOR_EXIT;
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        if(player.treasureCollected != 5){
            player.moveValid = false;
        } else{
            model.entities().remove(this);
            model.entities().add(new FloorTile(point));
        }
    }

}
