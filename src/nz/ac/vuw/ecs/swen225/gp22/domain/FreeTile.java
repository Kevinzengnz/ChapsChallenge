//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class FreeTile extends Tile{
    public FreeTile(Point point) {
        super(Sprite.FLOOR, point);
    }
}