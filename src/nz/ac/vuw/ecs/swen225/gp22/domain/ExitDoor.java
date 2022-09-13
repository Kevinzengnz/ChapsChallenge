//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

public class ExitDoor extends Door{
    public ExitDoor(Point point) {
        super(point);
        this.sprite = Sprite.DOOR_EXIT;
    }
}
