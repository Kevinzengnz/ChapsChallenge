//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class Treasure extends Collectable{
    public Treasure(Point point) {
        super(Sprite.COIN, point);
    }
}