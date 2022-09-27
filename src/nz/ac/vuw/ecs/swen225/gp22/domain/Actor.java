//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class Actor implements Entity{
    protected Sprite sprite;
    protected Point point;
    protected Direction direction = Direction.Down;
    protected int depth = 2;
    public Actor(Sprite sprite, Point point) {
        this.sprite = sprite;
        this.point = point;
    }
    public void setDirection(Direction direction){ this.direction = direction; }
    public Direction getDirection(){ return direction; }

    @Override
    public void ping(Model m) {

    }

    public Sprite getSprite() { return this.sprite; }
    public Point getPoint() {
        return this.point;
    }
    public int getDepth() {
        return this.depth;
    }
}

