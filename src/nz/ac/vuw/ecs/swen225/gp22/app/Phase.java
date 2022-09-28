package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
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
record Phase(Model model, Controller controller, Renderer renderer) {

    static Phase newLevel(Runnable next, Runnable first, List<Entity> levelEntities) {
        Renderer renderer = new Renderer();
        GameRecorder recorder = new GameRecorder();
        Player p = new Player(new Point(3, 4)); //default location of player
        levelEntities.add(p);

        //Adds tiles and walls to entity list
        for (int i=0;i<20;i++) {
            for (int j=0;j<20;j++) {
                if (i == 0 || j == 0 || i==19 || j == 19) {
                    levelEntities.add(new Tile(Sprite.WALL, new Point(i, j)));
                }
                else {
                    levelEntities.add(new Tile(Sprite.FLOOR, new Point(i, j)));
                }
            }
        }
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
        return new Phase(m, new Controller(m),renderer);
    }

    static Phase level1(Runnable next, Runnable first) {
        return newLevel(next, first, new ArrayList<>(List.of()));
    }
}
