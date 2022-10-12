package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents the Player in the game.
 * Player can move around and interact with other Entities.
 * @author Alicia Robinson - 300560663
 */
public class Player extends Actor{
    /**
     * Number of Treasures Player has collected
     */
    private int treasureCollected = 0;
    /**
     * Keys Player has collected
     */
    List<Key> keys = new ArrayList<>();
    /**
     * Boolean for if the player should currently be moving.
     */
    protected boolean moveValid = true;

    /**
     * Creates Player using given point and sprite.
     * @param point position of Player
     * @param sprite Player sprite
     */
    protected Player(Point point, String sprite) {
        super(sprite, point);
    }

    /**
     * Adds given key to the players key list
     * @param key key that player has picked up
     */
    protected void addKey(Key key){
        keys.add(key);
    }

    /**
     * Increases treasureCollected by 1
     */
    protected void addTreasure(){ treasureCollected += 1; }

    /**
     * @return number of treasures player has collected
     */
    public int getTreasureCollected() {
        return treasureCollected;
    }

    /**
     * @return players keys list
     */
    public List<Key> getKeys(){
        return keys;
    }

    /**
     * Removes given key from players keys list
     * @param key to be removed
     */
    public void removeKey(Key key){this.keys.remove(key);}

    /**
     * sets keys list to given list
     * @param keys list of keys
     */
    public void setKeys(List<Key> keys){
        if(keys == null){
            throw new IllegalArgumentException("Keys list is null");
        }
        this.keys = keys;
    }

    /**
     * Sets treasureCollected
     * @param treasureCollected number to set treasure collected to
     */
    public void setTreasureCollected(int treasureCollected){
        if(treasureCollected < 0){
            throw new IllegalArgumentException("Collected treasures cannot be less than 0");
        }
        this.treasureCollected = treasureCollected;
    }
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
    public String getSprite() {
        return switch (getDirection()) {
            case None  -> this.sprite;
            case Up    -> this.sprite = "PLAYER_UP";
            case Right -> this.sprite = "PLAYER_RIGHT";
            case Down  -> this.sprite = "PLAYER_DOWN";
            case Left  -> this.sprite = "PLAYER_LEFT";
        };
    }
}
