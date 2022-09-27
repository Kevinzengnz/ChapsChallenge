package nz.ac.vuw.ecs.swen225.gp22.renderer;


import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

public class WalkAnimation extends MoveAnimation{
    private int currentFrame = 0;
    private final int frameCount = 2;
    public WalkAnimation(Point startTile, Direction direction, int length, Entity entity) {
        super(startTile, direction, length, entity);
    }

    public WalkAnimation(Point startTile, Point endTile, int length, Entity entity) {
        super(startTile, Direction.Up, length, entity);
        if      (startTile.x() > endTile.x()) direction = Direction.Left;
        else if (startTile.x() < endTile.x()) direction = Direction.Right;
        else if (startTile.y() > endTile.y()) direction = Direction.Up;
        else if (startTile.y() < endTile.y()) direction = Direction.Down;
    }


    @Override
    public Sprite getSprite() {
        return switch (direction) {
            case None  -> null;
            case Up    -> currentFrame == 0 ? Sprite.PLAYER_WALK_UP_1 : Sprite.PLAYER_WALK_UP_2;
            case Right -> currentFrame == 0 ? Sprite.PLAYER_WALK_RIGHT_1 : Sprite.PLAYER_WALK_RIGHT_2;
            case Down  -> currentFrame == 0 ? Sprite.PLAYER_WALK_DOWN_1 : Sprite.PLAYER_WALK_DOWN_2;
            case Left  -> currentFrame == 0 ? Sprite.PLAYER_WALK_LEFT_1 : Sprite.PLAYER_WALK_LEFT_2;
        };
    }

    @Override
    public void ping() {
        super.ping();
        if (count % 2 == 0) {
            currentFrame ++;
            if (currentFrame >= frameCount) currentFrame = 0;
        }
    }

    @Override
    public Animation copy() {
        return new WalkAnimation(tile, direction, length, entity);
    }
}
