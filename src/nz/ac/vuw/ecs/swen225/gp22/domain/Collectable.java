package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents entities that the Player is able to pick up
 * @author Alicia Robinson - 300560663
 */
public class Collectable implements Entity{
    /**
     * String of the sprite name
     */
    private String sprite;
    /**
     * Point that Collectable is currently at
     */
    private Point point;
    /**
     * Sound effect that can be run when Collectable is interacted with
     */
    protected Runnable soundEffect;

    /**
     * Creates a Collectable using given sprite and point
     * @param sprite String name of the sprite
     * @param point point where the collectable is
     */
    protected Collectable(String sprite, Point point) {
        if(point == null || sprite == null){
            throw new IllegalArgumentException("Collectable Sprite or Point is null");
        }
        this.sprite = sprite;
        this.point = point;
    }
    /**
     * Creates a Collectable using given point
     * @param point point where the collectable is
     */
    protected Collectable(Point point) {
        this.point = point;
    }
    @Override
    public void setSoundEffect(Runnable soundEffect){
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }

    /**
     * Sets the sprite with the given string
     * @param sprite String name of sprite
     */
    protected void setSprite(String sprite){
        if(sprite == null){
            throw new IllegalArgumentException("Collectable Sprite is null");
        }
        this.sprite = sprite;
    }

    /**
     * Sets position of point
     * @param point position of point
     */
    protected void setPoint(Point point){
        if(point == null){
            throw new IllegalArgumentException("Collectable Sprite is null");
        }
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

