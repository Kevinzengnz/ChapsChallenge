package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Model
 * @author Kevin Zeng
 * ID: 300563468
 */
public interface Model{
    Player player();
    List<Entity> entities();
    void remove(Entity e);
    void onGameOver();
    void onNextLevel();

    default void ping(Renderer renderer){
        entities().forEach(a -> a.ping(this));
        renderer.update(player().getPoint().x(), player().getPoint().y(), entities(), new ArrayList<>());
        var end = false;
        if(end){ onNextLevel(); }
    }
}