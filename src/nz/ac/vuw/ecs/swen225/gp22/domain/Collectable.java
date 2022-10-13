package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents entities that the Player is able to pick up.
 *
 * @author Alicia Robinson - 300560663
 */
public class Collectable implements Entity {
    /**
     * String of the spriteName name.
     */
    private String spriteName;
    /**
     * Point that Collectable is currently at.
     */
    private Point point;
    /**
     * Sound effect that can be run when Collectable is interacted with.
     */
    public Runnable soundEffect;

    /**
     * Creates a Collectable using given spriteName and point.
     *
     * @param spriteName String name of the spriteName
     * @param point point where the collectable is
     */
    protected Collectable(String spriteName, Point point) {
        this.spriteName = spriteName;
        this.point = point;
    }

    /**
     * Creates a Collectable using given point.
     *
     * @param point point where the collectable is
     */
    protected Collectable(Point point) {
        this.point = point;
    }

    @Override
    public void setSoundEffect(Runnable soundEffect){
        if (soundEffect == null) {
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }

    /**
     * Sets the spriteName with the given string.
     *
     * @param spriteName String name of spriteName
     */
    protected void setSpriteName(String spriteName) {
        if (spriteName == null) {
            throw new IllegalArgumentException("Collectable Sprite is null");
        }
        this.spriteName = spriteName;
    }

    /**
     * Sets position of point.
     *
     * @param point position of point
     */
    protected void setPoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Collectable Sprite is null");
        }
        this.point = point;
    }

    @Override
    public String getSpriteName() {
        return this.spriteName;
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

