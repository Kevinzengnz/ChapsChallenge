package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Model class, which stores information on the game state.
 *
 * @author Kevin Zeng
 * ID: 300563468
 */
public interface Model {
  /**
   * Gets the time left in the game.
   *
   * @return time left in timer
   */
  int timeLeft();

  /**
   * Decreases timer.
   */
  void decrementTime();

  /**
   * Gets the player character of the game.
   *
   * @return player character
   */
  Player player();

  /**
   * Gets the list of entities in the level.
   *
   * @return List of entities
   */
  List<Entity> entities();

  /**
   * Gets the recorder for the level.
   *
   * @return GameRecorder object that is recording the level
   */
  GameRecorder recorder();

  /**
   * Removes an entity from the entity list.
   *
   * @param e entity to be removed.
   */
  void remove(Entity e);

  /**
   * Action to perform once game is lost.
   */
  void onGameOver();

  /**
   * Action to perform once game has been completed.
   */
  void onNextLevel();

  /**
   * Gets the total number of treasures at the start of the level.
   *
   * @return nnumber of treasures that were at the start of the level
   */
  long totalTreasures();

  /**
   * Gets the current level number.
   *
   * @return the current level number
   */
  int levelNumber();

  /**
   * Returns the number of Treasures left in the level.
   * This number will change as the player collects treasures in the level.
   *
   * @return number of treasures in the entities list
   */
  default long treasuresLeft() {
    return entities().stream().filter(e -> e instanceof Treasure).count();
  }

  /**
   * Saves the current game to an xml file in the format: "saveGame" + timeStamp.
   */
  default void saveGame() {
    try {
      String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
          .format(new java.util.Date());
      XmlParser.saveGame(entities(), "saveGame" + timeStamp, timeLeft());
      recorder().endRecording();
    } catch (IOException e) {
      System.out.println("Error saving game");
    }
  }

  /**
   * One step of the game.
   * "Pings" each entity in the model, ie each entity performs
   * whatever their designated action is for each step of the game.
   */
  default void ping() {
    entities().forEach(a -> a.ping(this));
    recorder().ping(player().getDirection().ordinal(), player().isMoving());
  }
}