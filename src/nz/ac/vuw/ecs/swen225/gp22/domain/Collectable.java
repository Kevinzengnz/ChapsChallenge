//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.awt.*;

public class Collectable implements Entity{
    protected Sprite sprite;
    protected Point point;
    protected int depth = 1;
    public Collectable(Sprite sprite, Point point) {
        this.sprite = sprite;
        this.point = point;
    }

    @Override
    public Sprite getSprite() { return this.sprite; }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }
}

