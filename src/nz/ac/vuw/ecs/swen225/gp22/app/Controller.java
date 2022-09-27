package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;

import java.awt.event.KeyEvent;

/**
 * Controller class
 * @author Kevin Zeng
 * ID: 300563468
 */
public class Controller extends Keys{

    /**
     * Initialises a new controller
     * @param c Chap player object
     */
    Controller(Player c){
        //UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
        setAction(KeyEvent.VK_UP,() -> { c.setMoving(true); c.setDirection(Direction.Up);},() -> c.setMoving(false));
        setAction(KeyEvent.VK_DOWN,() -> { c.setMoving(true); c.setDirection(Direction.Down);},() ->c.setMoving(false));
        setAction(KeyEvent.VK_LEFT,() -> { c.setMoving(true); c.setDirection(Direction.Left);},() -> c.setMoving(false));
        setAction(KeyEvent.VK_RIGHT,() -> { c.setMoving(true); c.setDirection(Direction.Right);},() ->c.setMoving(false));

        //WASD also moves Chap within the maze
        setAction(KeyEvent.VK_W,() -> { c.setMoving(true); c.setDirection(Direction.Up);},() -> c.setMoving(false));
        setAction(KeyEvent.VK_S,() -> { c.setMoving(true); c.setDirection(Direction.Down);},() -> c.setMoving(false));
        setAction(KeyEvent.VK_A,() -> { c.setMoving(true); c.setDirection(Direction.Left);},() -> c.setMoving(false));
        setAction(KeyEvent.VK_D,() -> { c.setMoving(true); c.setDirection(Direction.Right);},() -> c.setMoving(false));

        //SPACE - pause the game and display a “game is paused” dialog
        setAction(KeyEvent.VK_SPACE,() -> {},/*Pause Game method*/() -> System.out.println("game paused"));

        //ESC - close the “game is paused” dialog and resume the game
        setAction(KeyEvent.VK_ESCAPE,() -> {},/*Unpause*/() -> System.out.println("game unpaused"));

        //CTRL-X - exit the game, the current game state will be lost, the next time the game is
        //started, it will resume from the last unfinished level
        setCtrlAction(KeyEvent.VK_X,() -> {},() -> {System.out.println("game exit, no save");});

        //CTRL-S - exit the game, saves the game state, game will resume next time the
        //application will be started
        setCtrlAction(KeyEvent.VK_S,() -> {},() -> {System.out.println("game exit, saved");});

        //CTRL-R - resume a saved game -- this will pop up a file selector to select a saved game
        //to be loaded
        setCtrlAction(KeyEvent.VK_R,() -> {},() -> System.out.println("resume saved game"));

        //CTRL-1 - start a new game at level 1
        setCtrlAction(KeyEvent.VK_1,() -> {},() -> {System.out.println("level 1");});
        //CTRL-2 - start a new game at level 2
        setCtrlAction(KeyEvent.VK_2,() -> {},() -> {System.out.println("level 2");});
    }
}