package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
/**
 * @author Alicia Robinson - 300560663
 */
public class Treasure extends Collectable{
    protected Treasure(Point point) {
        super("TREASURE", point);
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal Treasure Point");
        }
        //TODO Check number of treasures collected and to be collected is correct
        soundEffect.run();
        player.addTreasure();
        model.remove(this);
    }
}
