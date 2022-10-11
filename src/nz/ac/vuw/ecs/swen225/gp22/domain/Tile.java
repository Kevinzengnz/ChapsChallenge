package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Tile implements Entity{
    private final String sprite;
    private final Point point;

    public Tile(String sprite, Point point) {
        if(point == null || sprite == null){
            throw new IllegalArgumentException("Tile Sprite or Point is null");
        }
        this.sprite = sprite;
        this.point = point;
    }

    @Override
    public String getSprite() {
        return this.sprite;
    }

    @Override
    public Point getPoint() { return this.point; }

    @Override
    public int getDepth() {
        return 0;
    }
}

