package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

/**
 * @author Alicia Robinson - 300560663
 */
public class Collectable implements Entity{
    protected Sprite sprite;
    private final Point point;

    protected Collectable(Sprite sprite, Point point) {
        //TODO check for null sprite and point
        this.sprite = sprite;
        this.point = point;
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

