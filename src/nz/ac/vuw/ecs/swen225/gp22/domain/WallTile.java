package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class WallTile extends Tile{
    public WallTile(Point point) {
        super(Sprite.WALL, point);
    }
}