package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Alicia Robinson - 300560663
 */
public class Player extends Actor{
    private int treasureCollected = 0;
    List<Key> keys = new ArrayList<>();
    protected boolean moveValid = true;
    protected Player(Point point) {
        super(Sprite.PLAYER_DOWN, point);
    }
    protected void addKey(Key key){
        keys.add(key);
    }
    protected void addTreasure(){ treasureCollected += 1; }
    public int getTreasureCollected() {
        return treasureCollected;
    }
    public List<Key> getKeys(){
        return keys;
    }
    //TODO Add remove method for keys list
    @Override
    public void ping(Model m) {
        if (isMoving()) {
            Point newPoint = point.add(direction.arrow);
            moveValid = true;
            m.entities().stream()
                    .filter(entity -> (!(entity instanceof FloorTile || entity instanceof Player)
                            && entity.getPoint().equals(newPoint)))
                    .forEach(entity -> entity.doAction(m, this, newPoint));
            if (moveValid) {
                this.point = newPoint;
            }

        }
    }
    @Override
    public Sprite getSprite() {
        return switch (getDirection()) {
            case None  -> this.sprite;
            case Up    -> this.sprite = Sprite.PLAYER_UP;
            case Right -> this.sprite = Sprite.PLAYER_RIGHT;
            case Down  -> this.sprite = Sprite.PLAYER_DOWN;
            case Left  -> this.sprite = Sprite.PLAYER_LEFT;
        };
    }
    public String toString() {return "Player";}
}
