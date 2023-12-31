package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;
import org.dom4j.Element;


/**
 * Phase of Chaps Challenge.
 *
 * @author Kevin Zeng
 *         ID: 300563468
 */
public record Phase(Model model, PlayerController controller, Renderer renderer) {

  /**
   * Returns a new level with the given list of entities.
   *
   * @param next          runnable to perform after finishing this level
   * @param first         runnable to perform after failing this level
   * @param levelEntities list of entities in this level
   * @param time          amount of time allowed to complete level
   * @param level         level number
   * @return new phase with the list of given entities
   */
  static Phase newLevel(Runnable next, Runnable first,
                        List<Entity> levelEntities, int time, int level) {
    Renderer renderer = new Renderer();
    GameRecorder recorder = new GameRecorder();
    Player p = levelEntities.stream().filter(a -> a instanceof Player).map(a -> (Player)
        a).findFirst().orElseThrow();
    renderer.ping(p.getPoint(), levelEntities, new ArrayList<>());
    long totalTreasures = levelEntities.stream().filter(e -> e instanceof Treasure).count();
    var m = new Model() {
      int timeLeft = time;
      List<Entity> entities = levelEntities;

      @Override
      public int timeLeft() {
        return timeLeft;
      }

      @Override
      public void decrementTime() {
        timeLeft -= 1;
      }

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

      @Override
      public long totalTreasures() {
        return totalTreasures;
      }

      @Override
      public int levelNumber() {
        return level;
      }
    };
    return new Phase(m, new PlayerController(p), renderer);
  }

  /**
   * Loads the first level from the levelOne.xml file
   *
   * @param next  runnable to perform after finishing this level
   * @param first runnable to perform after failing this level
   * @return new phase created from entities in levelOne file
   */
  static Phase level1(Runnable next, Runnable first) {
    String fileName = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/levelOne.xml";
    List<Entity> levelEntities = XmlParser
        .loadGame(fileName);
    return newLevel(next, first, levelEntities, XmlParser.getTime(), XmlParser.getLevel());
  }

  /**
   * Loads the second level from the levelTwo.xml file.
   *
   * @param next  runnable to perform after finishing this level
   * @param first runnable to perform after failing this level
   * @return new phase created from entities in levelTwo file
   */
  static Phase level2(Runnable next, Runnable first) {
    String fileName = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/levelTwo.xml";
    List<Entity> levelEntities = XmlParser
        .loadGame(fileName);
    return newLevel(next, first, levelEntities, XmlParser.getTime(), XmlParser.getLevel());
  }

  /**
   * Loads a level from a given file.
   *
   * @param fileName file to load game state from
   * @param next  runnable to perform after finishing this level
   * @param first runnable to perform after failing this level
   * @return new phase created from given file
   */
  static Phase loadLevel(String fileName, Runnable next, Runnable first) {
    List<Entity> levelEntities = XmlParser.loadGame(fileName);
    return newLevel(next, first, levelEntities, XmlParser.getTime(), XmlParser.getLevel());
  }

  /**
   * Loads a level from a given tiles elements, which has the list of entities.
   *
   * @param tiles       file to load game layout from
   * @param time        time left in save
   * @param levelNumber level number
   * @param next  runnable to perform after finishing this level
   * @param first runnable to perform after failing this level
   * @return new phase created from given file
   */
  static Phase loadLevel(Element tiles, int time, int levelNumber, Runnable next, Runnable first) {
    List<Entity> levelEntities = XmlParser.loadTiles(tiles);
    return newLevel(next, first, levelEntities, time, levelNumber);
  }
}
