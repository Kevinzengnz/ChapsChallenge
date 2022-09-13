package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.app.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Animation;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class MoveAnimation implements Animation {
    double x, y;
    Point startTile;
    Direction direction;

    Entity entity;

    int length;
    int tileSize = 64;

    int count;

    public MoveAnimation(Point startTile, Direction direction, int length, Entity entity) {
        this.startTile = startTile;
        this.direction = direction;
        this.x = startTile.x() * tileSize;
        this.y = startTile.y() * tileSize;
        this.length = length;
        this.count = length;
        this.entity = entity;
    }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    @Override
    public Sprite getSprite() {
        return entity.getSprite();
    }

    @Override
    public boolean isRunning() {
        return count > 0;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void update() {
        if (!isRunning()) return;

        double moveAmount = (double)tileSize / length;

        switch (direction) {
            case Right -> x += moveAmount;
            case Left -> x -= moveAmount;
            case Up -> y -= moveAmount;
            case Down -> y += moveAmount;
        }
        count--;
    }

    @Override
    public Animation copy() {
        return new MoveAnimation(startTile, direction, length, entity);
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
}
