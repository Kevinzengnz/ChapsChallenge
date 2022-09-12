package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;

/**
 *
 * @author Kevin Zeng
 * ID: 300563468
 */
class Controller extends Keys{

    Controller(Object c){
        //UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
        setAction(KeyEvent.VK_UP,c.set(Direction::up),c.set(Direction::unUp));
        setAction(KeyEvent.VK_DOWN,c.set(Direction::down),c.set(Direction::unDown));
        setAction(KeyEvent.VK_LEFT,c.set(Direction::left),c.set(Direction::unLeft));
        setAction(KeyEvent.VK_RIGHT,c.set(Direction::right),c.set(Direction::unRight));

        //SPACE - pause the game and display a “game is paused” dialog
        setAction(KeyEvent.VK_SPACE,/*Pause Game method*/() -> {},() -> {});

        //ESC - close the “game is paused” dialog and resume the game
        setAction(KeyEvent.VK_ESCAPE,/*Unpause*/() -> {},() -> {});

        //1. CTRL-X - exit the game, the current game state will be lost, the next time the game is
        //started, it will resume from the last unfinished level
        //2. CTRL-S - exit the game, saves the game state, game will resume next time the
        //application will be started
        //3. CTRL-R - resume a saved game -- this will pop up a file selector to select a saved game
        //to be loaded
        //4. CTRL-1 - start a new game at level 1
        //5. CTRL-2 - start a new game at level 2
        //comment

    }
}