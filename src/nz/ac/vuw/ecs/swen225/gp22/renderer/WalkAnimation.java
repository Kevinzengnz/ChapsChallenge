package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.app.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

public class WalkAnimation extends MoveAnimation{

    public WalkAnimation(Point startTile, Direction direction, int length, Entity entity) {
        super(startTile, direction, length, entity);
    }

    @Override
    public Sprite getSprite() {
        return switch (direction) {
            case None  -> null;
            case Up    -> Sprite.PLAYER_UP;
            case Right -> Sprite.PLAYER_RIGHT;
            case Down  -> Sprite.PLAYER_DOWN;
            case Left  -> Sprite.PLAYER_LEFT;
        };
    }
}
