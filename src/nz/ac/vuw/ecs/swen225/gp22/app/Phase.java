package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Phase of Chaps Challenge
 * @author Kevin Zeng
 * ID: 300563468
 */
record Phase(Model model, PlayerController controller, Renderer renderer) {

    static Phase newLevel(Runnable next, Runnable first, List<Entity> levelEntities) {
        Renderer renderer = new Renderer();
        GameRecorder recorder = new GameRecorder();
        Player p = levelEntities.stream().filter(a -> a instanceof Player).map(a -> (Player)
                a).findFirst().orElseThrow();
        renderer.ping(p.getPoint(), levelEntities, new ArrayList<>());
        var m = new Model() {
            List<Entity> entities = levelEntities;

            @Override
            public Player player() {
                return p;
            }

            @Override
            public List<Entity> entities() {
                return entities;
            }

            @Override
            public GameRecorder recorder() {
                return recorder;
            }

            @Override
            public void remove(Entity e) {
                entities = entities.stream()
                        .filter(ei -> !ei.equals(e))
                        .toList();
            }

            @Override
            public void onGameOver() {
                first.run();
            }

            @Override
            public void onNextLevel() {
                next.run();
            }
        };
        return new Phase(m, new PlayerController(p),renderer);
    }

    /**
     * Loads the first level from the levelOne.xml file
     * @param next runnable to perform after finishing this level
     * @param first runnable to perform after failing this level
     * @return new phase created from entities in levelOne file
     */
    static Phase level1(Runnable next, Runnable first) {
        List<Entity> levelEntities = XmlParser.loadGame("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/levelOne.xml");
        return newLevel(next, first, levelEntities);
    }

    /**
     * Loads the second level from the levelTwo.xml file
     * @param next runnable to perform after finishing this level
     * @param first runnable to perform after failing this level
     * @return new phase created from entities in levelTwo file
     */
    static Phase level2(Runnable next, Runnable first) {
        List<Entity> levelEntities = XmlParser.loadGame("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/levelTwo.xml");
        return newLevel(next, first, levelEntities);
    }

    static Phase loadLevel() {
        List<Entity> levelEntities = XmlParser.loadGame("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/levelOne.xml");

        return newLevel(()->{}, ()->{}, levelEntities);
    }
}
