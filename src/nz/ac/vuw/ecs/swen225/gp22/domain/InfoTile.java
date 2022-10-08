package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class InfoTile extends Tile{
    public String infoText;
    public InfoTile(Point point, String infoText) {
        super(Sprite.INFO, point);
        this.infoText = infoText;
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        //call to show info tile here? or set info tile talk to Oliver
    }
}
