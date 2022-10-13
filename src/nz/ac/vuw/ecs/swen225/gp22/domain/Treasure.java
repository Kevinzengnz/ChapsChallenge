package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents the Treasure that can be picked up by the Player.
 *
 * @author Alicia Robinson - 300560663
 */
public class Treasure extends Collectable {
    /**
     * Creates new treasure at given point.
     *
     * @param point position of treasure
     */
    protected Treasure(Point point) {
        super("TREASURE", point);
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if (!this.getPoint().equals(point)) {
            throw new IllegalArgumentException("Player point does not equal Treasure Point");
        }
        soundEffect.run();
        player.addTreasure();
        model.remove(this);
        assert model.totalTreasures() == player.getTreasureCollected() + model.treasuresLeft();
    }
}
