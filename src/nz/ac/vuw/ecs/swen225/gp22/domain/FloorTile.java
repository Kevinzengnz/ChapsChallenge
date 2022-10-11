package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class FloorTile extends Tile{
    protected FloorTile(Point point) {
        super("FLOOR", point);
    }
    @Override
    public String toString() {return this.getSprite();}
}
