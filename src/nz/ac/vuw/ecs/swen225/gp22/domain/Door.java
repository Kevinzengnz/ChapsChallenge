package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents Doors in the game they can be opened by a Player
 * @author Alicia Robinson - 300560663
 */
public class Door implements Entity{
    private String spriteName;
    private final Point point;
    public Runnable soundEffect;

    /**
     * Creates a door at the given point
     * @param point point that door is at
     */
    protected Door(Point point) {
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
     * @param spriteName String name of sprite
     */
    public void setSpriteName(String spriteName){
        if(spriteName == null){
            throw new IllegalArgumentException("Door Sprite Name is null");
        }
        this.spriteName = spriteName;
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

