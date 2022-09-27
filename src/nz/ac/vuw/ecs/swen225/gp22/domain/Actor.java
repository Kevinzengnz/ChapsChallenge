package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Actor implements Entity{
    boolean moving = false;
    protected Sprite sprite;
    protected Point point;
    protected Direction direction = Direction.Down;
    protected int depth = 2;
    public void setMoving(boolean moving){ this.moving = moving; }
    public boolean getMoving(){ return this.moving; }
    public Actor(Sprite sprite, Point point) {
        this.sprite = sprite;
        this.point = point;
    }
    public void setDirection(Direction direction){ this.direction = direction; }
    public Direction getDirection(){ return direction; }
    public Sprite getSprite() { return this.sprite; }
    public Point getPoint() {
        return this.point;
    }
    public int getDepth() {
        return this.depth;
    }
}

