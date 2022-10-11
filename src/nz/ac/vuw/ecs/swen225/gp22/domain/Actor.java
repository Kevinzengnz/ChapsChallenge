package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Actor implements Entity{
    private boolean moving = false;
    protected String sprite;
    protected Point point;
    protected Direction direction = Direction.Down;
    public void setMoving(boolean moving){ this.moving = moving; }
    protected boolean isMoving(){ return this.moving; }
    protected Actor(String sprite, Point point) {
        if(point == null || sprite == null){
            throw new IllegalArgumentException("Actor Sprite or Point is null");
        }
        this.sprite = sprite;
        this.point = point;
    }
    protected void setSprite(String sprite){ this.sprite = sprite; }
    public void setDirection(Direction direction){ this.direction = direction; }
    public Direction getDirection(){ return direction; }
    @Override
    public String getSprite() { return this.sprite; }
    @Override
    public Point getPoint() { return this.point; }
    @Override
    public int getDepth() { return 2; }
}

