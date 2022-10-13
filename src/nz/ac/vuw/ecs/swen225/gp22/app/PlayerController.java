package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.util.LinkedHashSet;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;

/**
 * PlayerController class, which has key controls for moving the player.
 *
 * @author Kevin Zeng
 *         ID: 300563468
 */
public class PlayerController extends Keys {
  /**
   * Set of keycodes that are currently held down.
   */
  private final LinkedHashSet<Integer> keysHeld = new LinkedHashSet<>();

  /**
   * Player that is being controlled.
   */
  private final Player player;

  /**
   * Initialises a new controller for the player character.
   *
   * @param p Player character
   */
  protected PlayerController(Player p) {
    this.player = p;
    unPause();
  }

  /**
   * Pauses the controller, which disables all controls while paused.
   */
  public void pause() {
    setAction(KeyEvent.VK_UP, () -> {
    }, () -> {
    });
    setAction(KeyEvent.VK_DOWN, () -> {
    }, () -> {
    });
    setAction(KeyEvent.VK_LEFT, () -> {
    }, () -> {
    });
    setAction(KeyEvent.VK_RIGHT, () -> {
    }, () -> {
    });
    setAction(KeyEvent.VK_W, () -> {
    }, () -> {
    });
    setAction(KeyEvent.VK_S, () -> {
    }, () -> {
    });
    setAction(KeyEvent.VK_A, () -> {
    }, () -> {
    });
    setAction(KeyEvent.VK_D, () -> {
    }, () -> {
    });
  }

  /**
   * Unpauses the controller, which restarts all the player keybindings.
   */
  public void unPause() {
    //UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
    setAction(KeyEvent.VK_UP,
        () -> {
          moveUp();
          keysHeld.add(KeyEvent.VK_UP);
        },
        () -> releaseDirection(KeyEvent.VK_UP));
    setAction(KeyEvent.VK_DOWN,
        () -> {
          moveDown();
          keysHeld.add(KeyEvent.VK_DOWN);
        },
        () -> releaseDirection(KeyEvent.VK_DOWN));
    setAction(KeyEvent.VK_LEFT,
        () -> {
          moveLeft();
          keysHeld.add(KeyEvent.VK_LEFT);
        },
        () -> releaseDirection(KeyEvent.VK_LEFT));
    setAction(KeyEvent.VK_RIGHT,
        () -> {
          moveRight();
          keysHeld.add(KeyEvent.VK_RIGHT);
        },
        () -> releaseDirection(KeyEvent.VK_RIGHT));

    //WASD also moves Chap within the maze
    setAction(KeyEvent.VK_W,
        () -> {
          moveUp();
          keysHeld.add(KeyEvent.VK_W);
        },
        () -> releaseDirection(KeyEvent.VK_W));
    setAction(KeyEvent.VK_S,
        () -> {
          moveDown();
          keysHeld.add(KeyEvent.VK_S);
        },
        () -> releaseDirection(KeyEvent.VK_S));
    setAction(KeyEvent.VK_A,
        () -> {
          moveLeft();
          keysHeld.add(KeyEvent.VK_A);
        },
        () -> releaseDirection(KeyEvent.VK_A));
    setAction(KeyEvent.VK_D,
        () -> {
          moveRight();
          keysHeld.add(KeyEvent.VK_D);
        },
        () -> releaseDirection(KeyEvent.VK_D));
  }

  /**
   * Begins moving the player up.
   */
  public void moveUp() {
    player.setMoving(true);
    player.setDirection(Direction.Up);
  }

  /**
   * Begins moving the player down.
   */
  public void moveDown() {
    player.setMoving(true);
    player.setDirection(Direction.Down);
  }

  /**
   * Begins moving the player left.
   */
  public void moveLeft() {
    player.setMoving(true);
    player.setDirection(Direction.Left);
  }

  /**
   * Begins moving the player right.
   */
  public void moveRight() {
    player.setMoving(true);
    player.setDirection(Direction.Right);
  }

  /**
   * Stops moving a player in one direction.
   * If any other keys are still held, moves player in that direction,
   * otherwise stops moving the player.
   */
  public void releaseDirection(int keyCode) {
    keysHeld.remove(keyCode);
    if (keysHeld.isEmpty()) {
      player.setMoving(false);
    } else {
      getActionsPressed().getOrDefault(keysHeld.iterator().next(), () -> {
      }).run();
    }
  }

}