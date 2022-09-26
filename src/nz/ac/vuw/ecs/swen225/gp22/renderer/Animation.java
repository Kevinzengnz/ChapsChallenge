package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Abstract superclass for all Animations
 * @author Oliver Silk
 * ID: 300564261
 */
abstract public class Animation {
    public Animation(Point tile, int length, Entity entity) {
        this.tile = tile;
        this.x = tile.x() * tileSize;
        this.y = tile.y() * tileSize;
        this.length = length;
        this.entity = entity;
    }
    Point tile;
    double x, y;
    Entity entity;
    int length;
    static final int tileSize = 64;
    int getX() {
        return (int) x;
    }
    int getY() {
        return (int) y;
    }
    Sprite getSprite() {
        return entity.getSprite();
    }
    abstract boolean isFinished();
    Entity getEntity() {
        return entity;
    }
    abstract void update();

    abstract Animation copy();
}
