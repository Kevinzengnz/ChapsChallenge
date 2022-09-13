package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.awt.*;

public interface Animation {
    int getX();
    int getY();
    Sprite getSprite();
    boolean isRunning();
    Entity getEntity();
    void update();

    Animation copy();
}
