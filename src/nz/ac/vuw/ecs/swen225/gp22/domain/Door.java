package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * @author Alicia Robinson - 300560663
 */
public class Door implements Entity{
    private String sprite;
    private final Point point;
    protected Runnable soundEffect;
    protected Door(Point point) {
        if(point == null){
            throw new IllegalArgumentException("Door Point is null");
        }
        this.point = point;
    }
    public void setSoundEffect(Runnable soundEffect){
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }

    public void setSprite(String sprite){
        if(sprite == null){
            throw new IllegalArgumentException("Door Sprite is null");
        }
        this.sprite = sprite;
    }

    @Override
    public String getSprite() {
        return this.sprite;
    }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public int getDepth() {
        return 1;
    }
}

