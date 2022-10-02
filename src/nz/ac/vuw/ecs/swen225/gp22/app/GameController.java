package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;

/**
 * GameController class
 * GameController has access to chaps challenge, and so has commands which
 * can interact with the state of the game, such as saving, loading,
 * pausing/unpausing and exiting the game
 * @author Kevin Zeng
 * ID: 300563468
 */
public class GameController extends Keys {

    /**
     * Initialises a new controller for the Chaps Challenge Game
     * @param c instance of ChapsChallenge
     */
    GameController(ChapsChallenge c) {
        //SPACE - pause the game and display a “game is paused” dialog
        setAction(KeyEvent.VK_SPACE,() -> {},c::pauseGame);

        //ESC - close the “game is paused” dialog and resume the game
        setAction(KeyEvent.VK_ESCAPE,() -> {},c::unPauseGame);

        //CTRL-X - exit the game, the current game state will be lost, the next time the game is
        //started, it will resume from the last unfinished level
        setCtrlAction(KeyEvent.VK_X,() -> {}, c::exitGame);

        //CTRL-S - exit the game, saves the game state, game will resume next time the
        //application will be started
        setCtrlAction(KeyEvent.VK_S,() -> {}, c::saveAndExit);

        //CTRL-R - resume a saved game -- this will pop up a file selector to select a saved game
        //to be loaded
        setCtrlAction(KeyEvent.VK_R,() -> {},c::loadGame);

        //CTRL-1 - start a new game at level 1
        setCtrlAction(KeyEvent.VK_1,() -> {}, c::levelOne);
        //CTRL-2 - start a new game at level 2
        setCtrlAction(KeyEvent.VK_2,() -> {}, c::levelTwo);
    }
}
