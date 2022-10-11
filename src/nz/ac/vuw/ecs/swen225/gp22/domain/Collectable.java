package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

/**
 * @author Alicia Robinson - 300560663
 */
public class Collectable implements Entity{
    private Sprite sprite;
    private final Point point;

    protected Collectable(Sprite sprite, Point point) {
        if(point == null || sprite == null){
            throw new IllegalArgumentException("Collectable Sprite or Point is null");
        }
        this.sprite = sprite;
        this.point = point;
    }
    protected void setSprite(Sprite sprite){
        this.sprite = sprite;
    }
    protected Collectable(Point point) {
        this.point = point;
    }

    @Override
    public Sprite getSprite() { return this.sprite; }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public int getDepth() {
        return 1;
    }
}

