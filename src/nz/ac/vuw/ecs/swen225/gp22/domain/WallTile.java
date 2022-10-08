package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class WallTile extends Tile{
    public WallTile(Point point) {
        super(Sprite.WALL, point);
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        player.moveValid = false;
    }
}