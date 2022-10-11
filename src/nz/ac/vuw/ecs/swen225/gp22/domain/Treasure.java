package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Treasure extends Collectable{
    protected Treasure(Point point) {
        super("TREASURE", point);
    }
    protected Runnable soundEffect;
    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal Treasure Point");
        }
        //TODO Check number of treasures collected and to be collected is correct
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        soundEffect.run();
        player.addTreasure();
        model.remove(this);
    }

    public void setSoundEffect(Runnable soundEffect){
        this.soundEffect = soundEffect;
    }
    @Override
    public String toString() {return this.getSprite();}
}
