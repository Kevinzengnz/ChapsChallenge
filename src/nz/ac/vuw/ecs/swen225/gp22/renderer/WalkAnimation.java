package nz.ac.vuw.ecs.swen225.gp22.renderer;


import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Animation to represent an Actor walking between two tiles.
 * Extends the MoveAnimation by cycling the Sprite through multiple frames.
 */
public class WalkAnimation extends MoveAnimation{
    /**
     * The current frame of the animation
     */
    private int currentFrame = 0;

    /**
     * The frames for the player's up animation.
     */
    private final Sprite[] upFrames = {Sprite.PLAYER_WALK_UP_1, Sprite.PLAYER_UP, Sprite.PLAYER_WALK_UP_2};
    /**
     * The frames for the player's down animation.
     */
    private final Sprite[] downFrames = {Sprite.PLAYER_WALK_DOWN_1, Sprite.PLAYER_DOWN, Sprite.PLAYER_WALK_DOWN_2};
    /**
     * The frames for the player's left animation.
     */
    private final Sprite[] leftFrames = {Sprite.PLAYER_WALK_LEFT_1, Sprite.PLAYER_LEFT, Sprite.PLAYER_WALK_LEFT_2};
    /**
     * The frames for the player's right animation.
     */
    private final Sprite[] rightFrames = {Sprite.PLAYER_WALK_RIGHT_1, Sprite.PLAYER_RIGHT, Sprite.PLAYER_WALK_RIGHT_2};

    /**
     * Constructs a new WalkAnimation
     * @param startTile the tile to start the animation in
     * @param direction the direction to move in from the startTile
     * @param length the length of the animation in ticks
     * @param entity the entity to animate
     */
    public WalkAnimation(Point startTile, Direction direction, int length, Entity entity) {
        super(startTile, direction, length, entity);
    }

    /**
     * Alternative constructor that has start and end tiles rather than a direction
     * @param startTile the tile to start the animation in
     * @param endTile the tile to end the animation in
     * @param length the length of the animation in ticks
     * @param entity the entity to animate
     */
    public WalkAnimation(Point startTile, Point endTile, int length, Entity entity) {
        super(startTile, Direction.Up, length, entity);
        if      (startTile.x() > endTile.x()) direction = Direction.Left;
        else if (startTile.x() < endTile.x()) direction = Direction.Right;
        else if (startTile.y() > endTile.y()) direction = Direction.Up;
        else if (startTile.y() < endTile.y()) direction = Direction.Down;
    }


    /**
     * @return the current sprite of the animation.
     */
    @Override
    public Sprite getSprite() {
        return switch (direction) {
            case None -> throw new IllegalStateException("WalkAnimation must have valid direction");
            case Up    -> upFrames[currentFrame];
            case Right -> rightFrames[currentFrame];
            case Down  -> downFrames[currentFrame];
            case Left  -> leftFrames[currentFrame];
        };
    }

    /**
     * Causes the animation to advance 1 tick and move in the direction set.
     * Advances the animation frame every two ticks.
     */
    @Override
    public void ping() {
        super.ping();
        if (count % 2 == 0) {
            currentFrame ++;
            int frameCount = 3;
            if (currentFrame >= frameCount) currentFrame = 0;
        }
    }

    /**
     * @return a new copy of the WalkAnimation
     */
    @Override
    public Animation copy() {
        return new WalkAnimation(tile, direction, length, entity);
    }
}
