package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class WallTile extends Tile{
    protected WallTile(Point point) {
        super("WALL", point);
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal WallTile Point");
        }
        player.moveValid = false;
    }
    @Override
    public String toString() {return this.getSprite();}
}