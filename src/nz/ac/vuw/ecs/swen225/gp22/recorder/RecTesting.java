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

  public RecTesting() {
    assert SwingUtilities.isEventDispatchThread();
  }

  public static void main(String[] a) {
    SwingUtilities.invokeLater(RecTesting::new);
  }
}
