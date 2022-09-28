package nz.ac.vuw.ecs.swen225.gp22.domain;
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
}
