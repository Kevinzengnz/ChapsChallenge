package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

public class MoveAnimation extends Animation {
    protected Direction direction = Direction.Up;

    private int count;

    public MoveAnimation(Point startTile, Direction direction, int length, Entity entity) {
        super(startTile, length, entity);
        this.direction = direction;
        this.x = tile.x() * tileSize;
        this.y = tile.y() * tileSize;
        this.count = length;
    }

    public MoveAnimation(Point startTile, Point endTile, int length, Entity entity) {
        super(startTile, length, entity);
        this.x = tile.x() * tileSize;
        this.y = tile.y() * tileSize;
        this.count = length;

        if (startTile.x() > endTile.x()) direction = Direction.Left;
        else if (startTile.x() < endTile.x()) direction = Direction.Right;
        else if (startTile.y() > endTile.y()) direction = Direction.Up;
        else if (startTile.y() < endTile.y()) direction = Direction.Down;
    }


    @Override
    public boolean isFinished() {
        return count <= 0;
    }

    @Override
    public void update() {
        if (isFinished()) return;
        double moveAmount = (double)tileSize / length;

        switch (direction) {
            case Right -> x += moveAmount;
            case Left  -> x -= moveAmount;
            case Up    -> y -= moveAmount;
            case Down  -> y += moveAmount;
        }
        count--;
    }

    @Override
    public Animation copy() {
        return new MoveAnimation(tile, direction, length, entity);
    }
}
