package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents a SewageTile in the game.
 * SewageTile Kill players when stepped on.
 *
 * @author Alicia Robinson - 300560663
 */
public class LavaTile extends Tile {
  /**
   * Sound effect made when player dies.
   */
  public Runnable soundEffect;

  /**
   * Creates new SewageTile with given sprite and point.
   *
   * @param point position of Tile
   */
  protected LavaTile(Point point) {
    super("LAVA", point);
  }

  @Override
  public void doAction(Model model, Player player, Point point) {
    if (!this.getPoint().equals(point)) {
      throw new IllegalArgumentException("Player point does not equal LavaTile Point");
    }
    soundEffect.run();
    model.onGameOver();
  }

  @Override
  public void setSoundEffect(Runnable soundEffect) {
    if (soundEffect == null) {
      throw new IllegalArgumentException("Sound Effect is Null");
    }
    this.soundEffect = soundEffect;
  }
}
