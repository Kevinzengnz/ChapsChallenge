package nz.ac.vuw.ecs.swen225.gp22.domain;
import java.util.function.Function;

/**
 * Abstract class for objects in the game which have direction and
 * can be controlled by the user
 * @author Alicia Robinson
 * ID: 300560663
 */
public abstract class ControllableDirection{
    private Direction direction=Direction.None;
    public Direction direction(){ return direction; }
    public void setDirection(Direction d){ direction=d; }
    public Runnable set(Function<Direction,Direction> f){
        return ()->direction=f.apply(direction);
    }
}
