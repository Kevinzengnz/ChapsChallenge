//Alicia Robinson 300560663
package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import java.util.ArrayList;
import java.util.List;

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
}
