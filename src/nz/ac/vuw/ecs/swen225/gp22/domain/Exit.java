package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
/**
 * @author Alicia Robinson - 300560663
 */
public class Exit implements Entity{
    private final Sprite sprite = Sprite.EXIT;
    private final Point point;
    protected Exit(Point point) {
        if(point == null){
            throw new IllegalArgumentException("Door Point is null");
        }
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
        return 1;
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal Exit Point");
        }
        if(player.getTreasureCollected() != 5){
            player.moveValid = false;
            throw new IllegalStateException("All treasures have not been collected, Exit should not be accessible");
        }
        model.onGameOver();
    }
    @Override
    public String toString() {return "Exit";}
}

