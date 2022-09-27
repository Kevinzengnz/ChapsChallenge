package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

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
    Tile[][] tiles();
    void onGameOver();
    void onNextLevel();

    default void ping(){
        entities().forEach(a -> {});
        var end = false;
        if(end){ onNextLevel(); }
    }
}