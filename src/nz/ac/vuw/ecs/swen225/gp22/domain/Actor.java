//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class Actor extends ControllableDirection implements Entity{
    protected Sprite sprite;
    protected Point point;
    protected int depth = 2;
    public Actor(Sprite sprite, Point point) {
        this.sprite = sprite;
        this.point = point;
    }

    public Sprite getSprite() { return this.sprite; }
    public Point getPoint() {
        return this.point;
    }
    public int getDepth() {
        return this.depth;
    }
}

