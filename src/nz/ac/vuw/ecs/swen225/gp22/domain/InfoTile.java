package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class InfoTile extends Tile{
    private String infoText;
    protected InfoTile(Point point) {
        super(Sprite.INFO, point);
    }
    public void setText(String infoText){
        //TODO check for blank text
        this.infoText = infoText;
    }
    public String getText(){
        return infoText;
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        //TODO InfoTile doAction
    }

    public String toString() {return "InfoTile";}
}
