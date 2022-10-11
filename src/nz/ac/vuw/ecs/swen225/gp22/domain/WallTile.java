package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class WallTile extends Tile{
    protected Runnable soundEffect;
    protected WallTile(Point point) {
        super("WALL", point);
    }
    public void setSoundEffect(Runnable soundEffect){
        this.soundEffect = soundEffect;
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal WallTile Point");
        }
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        soundEffect.run();
        player.moveValid = false;
    }
    @Override
    public String toString() {return this.getSprite();}
}