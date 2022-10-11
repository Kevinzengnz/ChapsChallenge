package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;

import java.awt.event.KeyEvent;
import java.util.*;

/**
 * PlayerController class
 * PlayerController only has keys for controlling/moving the player
 * @author Kevin Zeng
 * ID: 300563468
 */
public class PlayerController extends Keys{
    private LinkedHashSet<Integer> keysHeld = new LinkedHashSet<>();

    /**
     * Initialises a new controller for the player character
     * @param p Player character
     */
    PlayerController(Player p){
        Runnable moveUp = () -> { p.setMoving(true); p.setDirection(Direction.Up);};
        Runnable moveDown = () -> { p.setMoving(true); p.setDirection(Direction.Down);};
        Runnable moveLeft = () -> { p.setMoving(true); p.setDirection(Direction.Left);};
        Runnable moveRight = () -> { p.setMoving(true); p.setDirection(Direction.Right); };

        //UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
        setAction(KeyEvent.VK_UP,
                () -> { moveUp.run(); keysHeld.add(KeyEvent.VK_UP);},
                () -> releaseDirection(p,KeyEvent.VK_UP));
        setAction(KeyEvent.VK_DOWN,
                () -> { moveDown.run(); keysHeld.add(KeyEvent.VK_DOWN);},
                () -> releaseDirection(p,KeyEvent.VK_DOWN));
        setAction(KeyEvent.VK_LEFT,
                () -> { moveLeft.run();keysHeld.add(KeyEvent.VK_LEFT);},
                () -> releaseDirection(p,KeyEvent.VK_LEFT));
        setAction(KeyEvent.VK_RIGHT,
                () -> { moveRight.run(); keysHeld.add(KeyEvent.VK_RIGHT);},
                () -> releaseDirection(p,KeyEvent.VK_RIGHT));

        //WASD also moves Chap within the maze
        setAction(KeyEvent.VK_W,
                () -> { moveUp.run(); keysHeld.add(KeyEvent.VK_W);},
                () -> releaseDirection(p,KeyEvent.VK_W));
        setAction(KeyEvent.VK_S,() -> {moveDown.run(); keysHeld.add(KeyEvent.VK_S);},
                () -> releaseDirection(p,KeyEvent.VK_S));
        setAction(KeyEvent.VK_A,() -> {moveLeft.run(); keysHeld.add(KeyEvent.VK_A);},
                () -> releaseDirection(p,KeyEvent.VK_A));
        setAction(KeyEvent.VK_D,() -> {moveRight.run(); keysHeld.add(KeyEvent.VK_D);},
                () -> releaseDirection(p,KeyEvent.VK_D));
    }

    public void releaseDirection(Player p, int keyCode) {
        keysHeld.remove(keyCode);
        if(keysHeld.isEmpty()) {
            p.setMoving(false);
        } else {
            getActionsPressed().getOrDefault(keysHeld.iterator().next(), () -> {}).run();
        }
    }

}