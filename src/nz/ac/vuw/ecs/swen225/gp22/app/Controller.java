package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;

class Controller extends Keys{

    Controller(){
        //UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
        setAction(KeyEvent.VK_UP,c.set(Direction::up),c.set(Direction::unUp));
        setAction(KeyEvent.VK_DOWN,c.set(Direction::down),c.set(Direction::unDown));
        setAction(KeyEvent.VK_LEFT,c.set(Direction::left),c.set(Direction::unLeft));
        setAction(KeyEvent.VK_RIGHT,c.set(Direction::right),c.set(Direction::unRight));

        //SPACE - pause the game and display a “game is paused” dialog
        setAction(KeyEvent.VK_SPACE,/*Pause Game method*/() -> {},() -> {});

        //ESC - close the “game is paused” dialog and resume the game
        setAction(KeyEvent.VK_ESCAPE,/*Unpause*/() -> {},() -> {});
    }
}