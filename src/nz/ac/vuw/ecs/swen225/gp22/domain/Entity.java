package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.awt.*;

public interface Entity {
    Sprite getSprite();

    Point getPoint();

    int getDepth();
}
