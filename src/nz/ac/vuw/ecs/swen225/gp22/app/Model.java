package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;

import java.io.IOException;
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
    GameRecorder recorder();
    void remove(Entity e);
    void onGameOver();
    void onNextLevel();


    default void saveGame()   {
        try {
            XmlParser.saveGame(entities(), "levelOne");
        } catch(IOException e) {
            System.out.println("Error saving game");
        }
    }

    default void ping(Renderer renderer){
        entities().forEach(a -> a.ping(this));
        renderer.ping(player().getPoint(), entities(), new ArrayList<>());
        recorder().ping(player().getDirection().ordinal());
        var end = false;
        if(end){ onNextLevel(); }
    }
}