package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Exit implements Entity{
    private final String sprite = "EXIT";
    private final Point point;
    protected Runnable soundEffect;
    protected Exit(Point point) {
        if(point == null){
            throw new IllegalArgumentException("Door Point is null");
        }
        this.point = point;
    }
    public void setSoundEffect(Runnable soundEffect){
        this.soundEffect = soundEffect;
    }

    @Override
    public String getSprite() { return this.sprite; }

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
        //TODO pre condition checks needed
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal Exit Point");
        }
        if(model.treasuresLeft() != 0){
            player.moveValid = false;
            throw new IllegalStateException("All treasures have not been collected, Exit should not be accessible");
        }
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        soundEffect.run();
        model.onGameOver();
    }
    @Override
    public String toString() {return sprite;}
}

