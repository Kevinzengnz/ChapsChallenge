package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Recorder class that will handle recording a game.
 *
 * @author Sankeerth Alookaran
 *     ID: 300565439
 */
public class GameRecorder implements Recorder {
  private boolean isRecording;
  private String replayFile;
  private int level;
  private List<Action> movementHistory;
  private int prevDir = -1;
  private int frame = 0;
  private int startFrame = 0;
  private int endFrame = 0;
  private int timeLeft = -1;
  private Element tiles;

  /**
   * Start recording the current game into the specified save file path.
   *
   * @param m      Current game Model.
   * @param replay Name of replay file to be recorded.
   */
  @Override
  public void startRecording(final Model m, final String replay) {
    if (!this.isRecording) {
      this.tiles = XmlParser.getTilesElement(m);
      this.timeLeft = m.timeLeft();
      this.startFrame = this.frame;
      this.movementHistory = new ArrayList<>();
      this.level = m.levelNumber();
      this.replayFile = (replay.endsWith(".xml"))
          ? Arrays.stream(replay.split(".xml")).findFirst().orElse("default") : replay;
      setRecording(true);
      RecTesting.log("GameRecorder", "startRecording",
          "Starting recording for " + this.level + " at Replays/" + this.replayFile);
    }
  }

  /**
   * Ends the recording and saves the replay into the file
   * specified when recording was started.
   */
  @Override
  public void endRecording() {
    if (this.isRecording) {
      this.endFrame = this.frame;
      saveRecording();
      setRecording(false);
    }
  }

  /**
   * This function should be called on every step of the game clock.
   * Will record changes in player direction and the step it occurs
   * at to the replay file.
   *
   * @param dir      Ordinal of direction.
   * @param isMoving Boolean that indicates whether player is
   *                 currently moving.
   */
  @Override
  public void ping(final int dir, final boolean isMoving) {
    this.frame++;
    if (!this.isRecording) {
      return;
    }
    if (!isMoving) {
      if (this.prevDir == 0) {
        return;
      }
      this.prevDir = 0;
      Action a = new Action(0, this.frame);
      a.setEndFrame(this.frame);
      this.movementHistory.add(a);
      RecTesting.log("GameRecorder", "onAction", "Added stop");
      return;
    }
    if (this.prevDir == dir) {
      return;
    }
    this.prevDir = dir;
    this.movementHistory.get(this.movementHistory.size() - 1).setEndFrame(this.frame);
    this.movementHistory.add(new Action(dir, this.frame));
    RecTesting.log("GameRecorder", "onAction", "Added action " + dir);
  }

  /**
   * Sets recording state true or false.
   *
   * @param isRec true if game should be recorded.
   */
  private void setRecording(final boolean isRec) {
    this.isRecording = isRec;
  }

  /**
   * Saves recording into the replay file.
   */
  private void saveRecording() {
    Document doc = DocumentHelper.createDocument();
    Element root = doc.addElement("root");
    //Level data element.
    root.addElement("Level").addAttribute("name", String.valueOf(this.level)).addAttribute("time",
        String.valueOf(this.timeLeft));
    root.add(this.tiles);
    //Replay element.
    Element replay = root.addElement("Replay")
        .addAttribute("start", String.valueOf(this.startFrame))
        .addAttribute("end", String.valueOf(this.endFrame));
    Element player = replay.addElement("Player");
    //Store each action from action history into the player element.
    movementHistory.forEach(a -> player.addElement("action")
        .addAttribute(("dir"), String.valueOf(a.dir()))
        .addAttribute("frame", String.valueOf(a.frame()))
        .addAttribute("end", String.valueOf(a.endFrame())));
    //Write to replay file
    try {
      XmlParser.write(doc, this.replayFile, "Replays/");
      RecTesting.log("GameRecorder", "saveRecording", "Saved replay to Replays/" + this.replayFile);
    } catch (IOException e) {
      RecTesting.log("GameRecorder", "saveRecording", "IOException : " + e.getMessage());
    }
  }
}
