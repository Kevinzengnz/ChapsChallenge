package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents the Exit in the game.
 * Exit allows game to move to next level.
 *
 * @author Alicia Robinson - 300560663
 */
public class Exit implements Entity {
  /**
   * Point that Exit is at.
   */
  private final Point point;
  /**
   * Sound effect that can be run when Exit is interacted with.
   */
  public Runnable soundEffect;

  /**
   * Creates Exit at given point.
   *
   * @param point point that Exit is at
   */
  protected Exit(Point point) {
    this.point = point;
  }

  @Override
  public void setSoundEffect(Runnable soundEffect) {
    if (soundEffect == null) {
      throw new IllegalArgumentException("Sound Effect is Null");
    }
    this.soundEffect = soundEffect;
  }


  @Override
  public String getSpriteName() {
    return "EXIT";
  }


  @Override
  public Point getPoint() {
    return this.point;
  }


  @Override
  public int getDepth() {
    return 1;
  }

  @Override
  public void doAction(Model model, Player player, Point point) {
    if (!this.getPoint().equals(point)) {
      throw new IllegalArgumentException("Player point does not equal Exit Point");
    }
    if (model.treasuresLeft() != 0) {
      player.moveValid = false;
      throw new IllegalStateException("All treasures have not been collected, "
              + "Exit should not be accessible");
    }
    soundEffect.run();
    model.onNextLevel();
  }
}

