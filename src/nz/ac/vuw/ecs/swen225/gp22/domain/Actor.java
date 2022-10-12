package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents Actors in the game
 * Actors are able to move around
 * @author Alicia Robinson - 300560663
 */
public class Actor implements Entity{
    /**
     * boolean to tell if the Actor should be moving
     */
    private boolean moving = false;
    /**
     * String of the sprite name
     */
    protected String sprite;
    /**
     * Point that Actor is currently at
     */
    protected Point point;
    /**
     * direction Actor is facing
     * Initially set to Down
     */
    protected Direction direction = Direction.Down;
    protected Actor(String sprite, Point point) {
        this.sprite = sprite;
        this.point = point;
    }

    /**
     * Sets moving to true or false
     * @param moving boolean for if the Actor should be moving
     */
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    /**
     * Sets the Actors direction
     * @param direction direction that Actor is facing
     */
    public void setDirection(Direction direction){
        if(direction == null){
            throw new IllegalArgumentException("direction is null");
        }
        this.direction = direction;
    }

    /**
     * @return true if Actor is moving and false if they aren't
     */
    public boolean isMoving(){ return this.moving; }

    /**
     * @return direction that Actor is facing
     */
    public Direction getDirection(){ return direction; }
    @Override
    public String getSprite() { return this.sprite; }
    @Override
    public Point getPoint() { return this.point; }
    @Override
    public int getDepth() { return 2; }
}

