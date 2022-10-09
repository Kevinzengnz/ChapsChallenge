package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Treasure extends Collectable{
    public Treasure(Point point) {
        super(Sprite.COIN, point);
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        //TODO Check that player and treasure point are equal
        //TODO Check number of treasures collected and to be collected is correct
        player.addTreasure();
        model.remove(this);
    }
    public String toString() {return "Treasure";}
}
