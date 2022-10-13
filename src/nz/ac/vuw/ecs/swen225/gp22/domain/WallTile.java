package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents the Walls in the game.
 *
 * @author Alicia Robinson - 300560663
 */
public class WallTile extends Tile {
    public Runnable soundEffect;

    /**
     * Creates new WallTile at the given point.
     *
     * @param point position of WallTile
     */
    protected WallTile(Point point) {
        super("WALL", point);
    }

    @Override
    public void setSoundEffect(Runnable soundEffect) {
        if (soundEffect == null) {
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if (!this.getPoint().equals(point)) {
            throw new IllegalArgumentException("Player point does not equal WallTile Point");
        }
        soundEffect.run();
        player.moveValid = false;
    }

    @Override
    public void checkRobotMove(Model model, Robot robot, Point point) {
        if (!this.getPoint().equals(point)) {
            throw new IllegalArgumentException("Robot point does not equal WallTile Point");
        }
        robot.moveValid = false;
    }
}