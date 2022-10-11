package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;

import java.util.Objects;
/**
 * @author Alicia Robinson - 300560663
 */
public class Key extends Collectable{
    private final Colours colour;
    protected Runnable soundEffect;
    protected Key(Point point, String colourString) {
        super(point);
        if(colourString.isEmpty()){
            throw new IllegalArgumentException("Key colour is null");
        }
        colour = getColour(colourString);
        this.setSprite(colour.key);
    }
    public void setSoundEffect(Runnable soundEffect){
        this.soundEffect = soundEffect;
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal Key Point");
        }
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        soundEffect.run();
        player.addKey(this);
        model.remove(this);
        assert player.keys.contains(this);
        assert !model.entities().contains(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return colour == key.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour);
    }

    public String getColour(){
        return this.colour.getName();
    }
    @Override
    public String toString() {return "Key_"+getColour();}
}