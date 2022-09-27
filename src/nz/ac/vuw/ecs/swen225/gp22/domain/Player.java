package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Alicia Robinson - 300560663
 */
public class Player extends Actor{
    int treasureCollected = 0;
    List<Key> keys = new ArrayList<>();
    public Player(Point point) {
        super(Sprite.PLAYER_DOWN, point);
    }
    public void addKey(Key key){
        keys.add(key);
    }
    public void addTreasure(){ treasureCollected += 1; }
    public int getTreasureCollected() {
        return treasureCollected;
    }
    public List<Key> getKeys(){
        return keys;
    }
    @Override
    public void ping(Model m){
        Point oldPoint = this.getPoint();
        point.add(direction.arrow);
        Point newPoint = this.getPoint();
        if(!oldPoint.equals(newPoint)){
            for (Entity entity : m.entities()) {
                if (entity.getPoint().equals(this.getPoint()) && !entity.equals(this)) {
                    if (entity instanceof WallTile) {
                        throw new IllegalArgumentException("player cannot be moved into a wall");
                    }
                    else if (entity instanceof Key) {
                        this.addKey((Key) entity);
                        m.remove(entity);
                    }
                    else if (entity instanceof Treasure) {
                        m.remove(entity);
                        this.addTreasure();
                    }
                }
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
}
