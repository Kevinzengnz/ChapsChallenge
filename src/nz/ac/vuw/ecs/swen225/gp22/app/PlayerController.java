package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;

import java.awt.event.KeyEvent;

/**
 * PlayerController class
 * PlayerController only has keys for controlling/moving the player
 * @author Kevin Zeng
 * ID: 300563468
 */
public class PlayerController extends Keys{

    /**
     * Initialises a new controller for the player character
     * @param p Player character
     */
    PlayerController(Player p){
        //UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
        setAction(KeyEvent.VK_UP,() -> { p.setMoving(true); p.setDirection(Direction.Up);},() -> p.setMoving(false));
        setAction(KeyEvent.VK_DOWN,() -> { p.setMoving(true); p.setDirection(Direction.Down);},() ->p.setMoving(false));
        setAction(KeyEvent.VK_LEFT,() -> { p.setMoving(true); p.setDirection(Direction.Left);},() -> p.setMoving(false));
        setAction(KeyEvent.VK_RIGHT,() -> { p.setMoving(true); p.setDirection(Direction.Right);},() ->p.setMoving(false));

        //WASD also moves Chap within the maze
        setAction(KeyEvent.VK_W,() -> { p.setMoving(true); p.setDirection(Direction.Up);},() -> p.setMoving(false));
        setAction(KeyEvent.VK_S,() -> { p.setMoving(true); p.setDirection(Direction.Down);},() -> p.setMoving(false));
        setAction(KeyEvent.VK_A,() -> { p.setMoving(true); p.setDirection(Direction.Left);},() -> p.setMoving(false));
        setAction(KeyEvent.VK_D,() -> { p.setMoving(true); p.setDirection(Direction.Right);},() -> p.setMoving(false));
    }


}