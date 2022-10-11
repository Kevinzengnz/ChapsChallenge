package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

/**
 * @author Alicia Robinson - 300560663
 */
public class Door implements Entity{
    private Sprite sprite;
    private final Point point;
    protected Door(Point point) {
        if(point == null){
            throw new IllegalArgumentException("Door Point is null");
        }
        this.point = point;
    }

    public void setSprite(Sprite sprite){
        if(sprite == null){
            throw new IllegalArgumentException("Door Sprite is null");
        }
        this.sprite = sprite;
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
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

