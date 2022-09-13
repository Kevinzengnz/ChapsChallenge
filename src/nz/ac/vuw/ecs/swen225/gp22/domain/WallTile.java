//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class WallTile extends Tile{

    public WallTile(Sprite sprite, Point point) {
        super(Sprite.WALL, point);
    }
}