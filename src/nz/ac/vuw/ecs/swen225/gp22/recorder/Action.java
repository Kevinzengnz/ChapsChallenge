package nz.ac.vuw.ecs.swen225.gp22.recorder;

/**
 * Action class used for storing movement in replay files.
 *
 * @author Sankeerth Alookaran Jivan
 *     ID: 300565439
 */
class Action {
  private final int dir;
  private final int frame;
  private int endFrame = -1;

  /**
   * Constructor for action.
   *
   * @param direction Direction player changed to
   * @param crntFrame Frame number of direction changed
   */
  Action(final int direction, final int crntFrame) {
    this.dir = direction;
    this.frame = crntFrame;
  }

  /**
   * Constructor for action.
   *
   * @param direction Direction player changed to
   * @param crntFrame Frame number of direction changed
   * @param end End frame of movement
   */
  Action(final int direction, final int crntFrame, final int end) {
    this.dir = direction;
    this.frame = crntFrame;
    this.endFrame = end;
  }

  //GETTERS
  public int dir() {
    return this.dir;
  }

  public int frame() {
    return this.frame;
  }

  public int endFrame() {
    return  this.endFrame;
  }

  public void setEndFrame(int i) {
    this.endFrame = i;
  }
}
