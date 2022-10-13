package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Interface for recorder that can record gameplay.
 *
 * @author Sankeerth Alookaran Jivan
 *     ID: 300565439
 */
public interface Recorder {
  /**
   * Start recording the current game into the specified save file path.
   *
   * @param replayFile File to save recording into.
   * @param model      Current game Model.
   */
  void startRecording(Model model, String replayFile);

  /**
   * Ends the recording of the game.
   */
  void endRecording();

  /**
   * Call this function every time an action takes place in the game.
   *
   * @param dir      Ordinal of Direction.
   * @param isMoving Boolean that indicates whether player is currently moving.
   */
  void ping(int dir, boolean isMoving);
}
