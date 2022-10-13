package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import nz.ac.vuw.ecs.swen225.gp22.app.ChapsChallenge;
import nz.ac.vuw.ecs.swen225.gp22.app.PlayerController;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * Replay class that will handle replaying a saved game.
 *
 * @author Sankeerth Alookaran
 *     ID: 300565439
 */
public class Replay {
  private static List<Action> actionList;
  private static int pings = 0;
  private static double speed = 1;
  private static Timer timer = null;
  private static boolean isRunning = false;
  private static int endPing = 1;
  private static int frames = 0;
  private static ChapsChallenge pc;
  private static Element tiles;
  private static int timeLeft;
  private static int levelNumber;

  /**
   * Loads replay file data into this replay instance.
   *
   * @param replayName File name of recording.
   */
  public static void loadReplay(String replayName, ChapsChallenge game) {
    cleanReplay();
    pc = game;
    Document replay = null;
    try {
      replay = XmlParser.parse(new File(replayName));
    } catch (DocumentException de) {
      RecTesting.log("Replay", "loadReplay", "Error loading replay file");
    }
    tiles = replay.getRootElement().element("Tiles");
    timeLeft = Integer.parseInt(replay.getRootElement().element("Level")
        .attribute("time").getValue());
    levelNumber =
        Integer.parseInt(replay.getRootElement().element("Level").attribute("name").getValue());
    Element replayElem = replay.getRootElement().element("Replay");
    Element player = replayElem.element("Player");
    endPing = Integer.parseInt(replayElem.attribute("end").getValue());
    pings = Integer.parseInt(replayElem.attribute("start").getValue());
    List<Element> actions = player.elements("action");
    for (Element action : actions) {
      actionList.add(new Action(Integer.parseInt(action.attribute("dir").getValue()),
          Integer.parseInt(action.attribute("frame").getValue())));
    }
    actionList.remove(0);
    RecTesting.log("Replay", "loadReplay", "Replay loaded");
  }

  /**
   * Plays through replay until the end automatically.
   */
  public static void autoPlay() {
    if (isRunning) {
      return;
    }
    //Check if there is anything to replay.
    if (actionList == null || actionList.isEmpty()) {
      RecTesting.log("Replay", "autoPlay", "No actions to replay");
      return;
    }
    isRunning = true;
    RecTesting.log("Replay", "autoPlay", "Auto play started");
    //Restart timer if it already exists and is paused.
    if (timer != null) {
      timer.restart();
      return;
    }
    //Otherwise create a new timer and start it.
    timer = new Timer(1000 / 30, x -> {
      assert SwingUtilities.isEventDispatchThread();
      frames++;
      if (frames % (int) (4 / speed) == 0) {
        pings++;
      }
      step();
    });
    timer.start();
  }

  /**
   * Pauses automatic playback of the replay. Can be resumed later using autoPlay().
   */
  public static void autoPause() {
    if (!isRunning) {
      return;
    }
    isRunning = false;
    timer.stop();
  }

  /**
   * Sets playback speed multiplier for automatic playback of the replay.
   *
   * @param mul Speed multiplier for automatic playback of replay. Must be greater than 0.
   */
  public static void setReplaySpeed(double mul) {
    speed = (mul > 0) ? mul : speed;
  }

  /**
   * Plays replay step by step.
   * Call this method to move to the next step.
   * Pauses automatic playback.
   */
  public static void nextTick() {
    autoPause();
    pings++;
    step();
  }

  public static Element getTiles(){
    return tiles;
  }

  public static int getTimeLeft(){
    return timeLeft;
  }

  public static int getLevelNumber() {
    return levelNumber;
  }

  /**
   * Move to previous game tick in replay. Pauses autoplay.
   */
  private void prevTick() {
    autoPause();
  }

  /**
   * Returns whether replay is running or not.
   *
   * @return Whether automatic playback is running or not.
   */
  public static boolean isRunning() {
    return isRunning;
  }

  /**
   * Move to the next tick of the game clock in the replay.
   */
  private static void step() {
    PlayerController controller = pc.getPhase().controller();
    if (pings == endPing) {
      if (timer != null) {
        timer.stop();
        pc.pauseGame();
      }
      controller.releaseDirection(KeyEvent.VK_W);
      RecTesting.log("Replay", "autoPlay", "Replay stopped at frame " + pings);
      cleanReplay();
    }
    actionList.stream().filter(a -> a.frame() == pings).findFirst().ifPresentOrElse(
        (a) -> {
          RecTesting.log("Replay", "autoPlay",
              "Direction changed to: " + a.dir() + " at ping " + a.frame());
          switch (a.dir()) {
            case 0 -> controller.releaseDirection(KeyEvent.VK_W);
            case 1 -> controller.moveUp();
            case 2 -> controller.moveRight();
            case 3 -> controller.moveDown();
            case 4 -> controller.moveLeft();
            default -> {
            }
          }
        },
        () -> {
        }
    );
  }

  /**
   * Resets all fields back to default.
   */
  private static void cleanReplay() {
    isRunning = false;
    actionList = new ArrayList<>();
    pings = 0;
    endPing = 1;
    if (timer != null) {
      timer.stop();
      timer = null;
    }
    frames = 0;
  }
}
