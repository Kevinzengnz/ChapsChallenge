package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Class to represent an animation that performs a move from one tile to another.
 * @author Oliver Silk
 * ID: 300564261
 */
public class MoveAnimation extends Animation {
    protected Direction direction;

    protected int count;

    /**
     * Creates a new move Animation
     * @param startTile the tile to start the animation on
     * @param direction the tile to move the entity to
     * @param length the length of the animation in ticks
     * @param entity the entity to move
     */
    public MoveAnimation(Point startTile, Direction direction, int length, Entity entity) {
        super(startTile, length, entity);
        this.direction = direction;
        this.x = tile.x() * tileSize;
        this.y = tile.y() * tileSize;
        this.count = length;
    }

    /**
     * @return whether the animation has finished
     */
    @Override
    public boolean isFinished() {
        return count <= 0;
    }

    /**
     * Causes the animation to advance 1 tick and move in the direction set.
     */
    @Override
    public void ping() {
        if (isFinished()) return;
        double moveAmount = (double)tileSize / length;

        switch (direction) {
            case Right -> x += moveAmount;
            case Left  -> x -= moveAmount;
            case Up    -> y -= moveAmount;
            case Down  -> y += moveAmount;
            case None -> throw new IllegalStateException("MoveAnimation must have valid direction");
        }
        count--;
    }

    /**
     * @return a new copy of the MoveAnimation
     */
    @Override
    public Animation copy() {
        return new MoveAnimation(tile, direction, length, entity);
    }
}
