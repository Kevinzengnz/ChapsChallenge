package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;

import java.awt.event.KeyEvent;
import java.util.*;

/**
 * PlayerController class, which has key controls for moving the player.
 *
 * @author Kevin Zeng
 * ID: 300563468
 */
public class PlayerController extends Keys {
    /**
     * Set of keycodes that are currently held down.
     */
    private final LinkedHashSet<Integer> keysHeld = new LinkedHashSet<>();

    /**
     * Player that is being controlled.
     */
    private final Player p;

    /**
     * Initialises a new controller for the player character.
     *
     * @param p Player character
     */
    protected PlayerController(Player p) {
        this.p = p;
        unPause();
    }

    /**
     * Pauses the controller, which disables all controls while paused.
     */
    public void pause() {
        setAction(KeyEvent.VK_UP, () -> {}, () -> {});
        setAction(KeyEvent.VK_DOWN, () -> {}, () -> {});
        setAction(KeyEvent.VK_LEFT, () -> {}, () -> {});
        setAction(KeyEvent.VK_RIGHT, () -> {}, () -> {});
        setAction(KeyEvent.VK_W, () -> {}, () -> {});
        setAction(KeyEvent.VK_S, () -> {}, () -> {});
        setAction(KeyEvent.VK_A, () -> {}, () -> {});
        setAction(KeyEvent.VK_D, () -> {}, () -> {});
    }

    /**
     * Unpauses the controller, which restarts all the player keybindings.
     */
    public void unPause(){
        //UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
        setAction(KeyEvent.VK_UP,
                () -> { moveUp(); keysHeld.add(KeyEvent.VK_UP); },
                () -> releaseDirection(p, KeyEvent.VK_UP));
        setAction(KeyEvent.VK_DOWN,
                () -> { moveDown(); keysHeld.add(KeyEvent.VK_DOWN); },
                () -> releaseDirection(p, KeyEvent.VK_DOWN));
        setAction(KeyEvent.VK_LEFT,
                () -> { moveLeft();keysHeld.add(KeyEvent.VK_LEFT); },
                () -> releaseDirection(p, KeyEvent.VK_LEFT));
        setAction(KeyEvent.VK_RIGHT,
                () -> { moveRight(); keysHeld.add(KeyEvent.VK_RIGHT); },
                () -> releaseDirection(p, KeyEvent.VK_RIGHT));

        //WASD also moves Chap within the maze
        setAction(KeyEvent.VK_W,
                () -> { moveUp(); keysHeld.add(KeyEvent.VK_W); },
                () -> releaseDirection(p, KeyEvent.VK_W));
        setAction(KeyEvent.VK_S,
                () -> {moveDown(); keysHeld.add(KeyEvent.VK_S); },
                () -> releaseDirection(p, KeyEvent.VK_S));
        setAction(KeyEvent.VK_A,
                () -> {moveLeft(); keysHeld.add(KeyEvent.VK_A); },
                () -> releaseDirection(p, KeyEvent.VK_A));
        setAction(KeyEvent.VK_D,
                () -> {moveRight(); keysHeld.add(KeyEvent.VK_D); },
                () -> releaseDirection(p, KeyEvent.VK_D));
    }

    /**
     * Begins moving the player up.
     */
    private void moveUp() {
        p.setMoving(true);
        p.setDirection(Direction.Up);
    }

    /**
     * Begins moving the player down.
     */
    private void moveDown() {
        p.setMoving(true);
        p.setDirection(Direction.Down);
    }

    /**
     * Begins moving the player left.
     */
    private void moveLeft() {
        p.setMoving(true);
        p.setDirection(Direction.Left);
    }

    /**
     * Begins moving the player right.
     */
    private void moveRight() {
        p.setMoving(true);
        p.setDirection(Direction.Right);
    }

    /**
     * Stops moving a player in one direction.
     * If any other keys are still held, moves player in that direction,
     * otherwise stops moving the player.
     */
    public void releaseDirection(Player p, int keyCode) {
        keysHeld.remove(keyCode);
        if (keysHeld.isEmpty()) {
            p.setMoving(false);
        } else {
            getActionsPressed().getOrDefault(keysHeld.iterator().next(), () -> {}).run();
        }
    }

}