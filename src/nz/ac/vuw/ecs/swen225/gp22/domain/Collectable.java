package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * @author Alicia Robinson - 300560663
 */
public class Collectable implements Entity{
    private String sprite;
    private final Point point;
    protected Runnable soundEffect;

    protected Collectable(String sprite, Point point) {
        if(point == null || sprite == null){
            throw new IllegalArgumentException("Collectable Sprite or Point is null");
        }
        this.sprite = sprite;
        this.point = point;
    }
    public void setSoundEffect(Runnable soundEffect){
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }
    protected void setSprite(String sprite){
        this.sprite = sprite;
    }
    protected Collectable(Point point) {
        this.point = point;
    }

    @Override
    public String getSprite() { return this.sprite; }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public int getDepth() {
        return 1;
    }
}

