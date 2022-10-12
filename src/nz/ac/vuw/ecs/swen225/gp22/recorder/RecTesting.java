package nz.ac.vuw.ecs.swen225.gp22.recorder;

import javax.swing.SwingUtilities;

/**
 * Class containing test utilities for recorder package. Will be deleted after.
 */
public class RecTesting {
  private static final boolean DEBUG = false; //Set to true to show console debug messages.
  private static final String TEST_REPLAY_FILE = "test_replay";

  /**
   * Log message in console.
   *
   * @param cls     Class message originates from.
   * @param method  Method message originates from.
   * @param message Message to display in console.
   */
  public static void log(String cls, String method, String message) {
    if (DEBUG) {
      System.out.println("[" + cls + "]" + " " + method + ":: " + message);
    }
  }

  /**
   * Creates a mock replay for testing.
   */
  public static void createMockReplay() {
    Recorder r = new GameRecorder();
    r.startRecording(TEST_REPLAY_FILE, TEST_REPLAY_FILE);
    for (int i = 0; i < 60; i++) {
      if (i % 2 == 0) {
        r.ping(i % 3, true);
        r.ping(i % 3, true);
      }
    }
    r.endRecording();
  }

  public RecTesting() {
    assert SwingUtilities.isEventDispatchThread();
    createMockReplay();
  }

  public static void main(String[] a) {
    SwingUtilities.invokeLater(RecTesting::new);
  }
}
