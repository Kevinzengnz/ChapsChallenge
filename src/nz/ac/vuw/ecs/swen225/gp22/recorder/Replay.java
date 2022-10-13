package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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
  private static Timer timer = null;
  private static boolean isRunning = false;
  private static int pings = 0;
  private static int endPing = 1;
  private static int frames = 0;
  private static int speed = 4;
  private static int timeLeft;
  private static int levelNumber;
  private static ChapsChallenge pc;
  private static Element tiles;


  /**
   * Loads replay file data into this replay instance.
   *
   * @param replayName File name of recording.
   */
  public static void loadReplay(String replayName, ChapsChallenge game) throws IOException {
    cleanReplay();
    pc = game;
    Document replay;
    try {
      replay = XmlParser.parse(new File(replayName));
    } catch (DocumentException de) {
      throw new IOException("Invalid replay file");
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
          Integer.parseInt(action.attribute("frame").getValue()),
          Integer.parseInt(action.attribute("end").getValue())));
    }
    actionList.remove(0);
  }

  /**
   * Plays through replay until the end automatically.
   */
  public static void autoPlay() {
    if (isRunning || pc == null) {
      return;
    }
    //Check if there is anything to replay.
    if (actionList == null || actionList.isEmpty()) {
      return;
    }
    isRunning = true;
    //Restart timer if it already exists and is paused.
    if (timer != null) {
      timer.restart();
      pc.unPauseGame();
      pc.getPhase().controller().pause();
      return;
    }
    pc.unPauseGame();
    pc.getPhase().controller().pause();
    //Otherwise create a new timer and start it.
    timer = new Timer(1000 / 30, x -> {
      assert SwingUtilities.isEventDispatchThread();
      if (isRunning()) {
        pc.setClockSpeed(speed);
      }
      frames++;
      if (frames % speed == 0) {
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
    pc.pauseGame();
    pc.setClockSpeed(4);
    isRunning = false;
    timer.stop();
  }

  /**
   * Increase replay speed to have one less frame per ping. Does not go lower than 2 frames per
   * ping.
   */
  public static void increaseSpeed() {
    if (speed > 2) {
      speed--;
    }
  }

  /**
   * Decrease replay speed to have one more frame per ping. Does not go higher than 8 frames per
   * ping.
   */
  public static void decreaseSpeed() {
    if (speed < 8) {
      speed++;
    }
  }

  /**
   * Plays replay step by step.
   * Call this method to move to the next step.
   * Pauses automatic playback.
   */
  public static void nextTick() {
    if (pc == null) {
      return;
    }
    autoPause();
    pc.unPauseGame();
    pc.getPhase().controller().unPause();
    if (timer == null) {
      timer = new Timer(1000 / 30, x -> {
        assert SwingUtilities.isEventDispatchThread();
        frames++;
        if (frames % speed == 0) {
          pings++;
        }
        step();
      });
    }
    timer.start();
  }

  public static Element getTiles() {
    return tiles;
  }

  public static int getTimeLeft() {
    return timeLeft;
  }

  public static int getLevelNumber() {
    return levelNumber;
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
        pc.setClockSpeed(4);
      }
      controller.releaseDirection(KeyEvent.VK_W);
      cleanReplay();
    }
    actionList.stream().filter(a -> a.frame() == pings).findFirst().ifPresentOrElse(
        (a) -> {
          switch (a.dir()) {
            case 0 -> controller.releaseDirection(KeyEvent.VK_W);
            case 1 -> controller.moveUp();
            case 2 -> controller.moveRight();
            case 3 -> controller.moveDown();
            case 4 -> controller.moveLeft();
            default -> {
            }
          }
          if (!isRunning()) {
            if (a.endFrame() == pings) {
              controller.releaseDirection(KeyEvent.VK_W);
              timer.stop();
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
    pc = null;
  }
}
