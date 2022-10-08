package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Exit implements Entity{
    protected Sprite sprite = Sprite.EXIT;
    protected Point point;
    protected int depth = 1;
    public Exit(Point point) {
        this.point = point;
    }

    @Override
    public Sprite getSprite() { return this.sprite; }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if(player.treasureCollected != 5){
            throw new IllegalStateException("All treasures have not been collected, Exit should not be accessible");
        }
        //game over call here
    }
}

