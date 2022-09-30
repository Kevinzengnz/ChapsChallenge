/**
 * The application module provides a Graphical User Interface implemented using swing.
 * The player can see the maze and interact with it through the following keystrokes:
 * 1. CTRL-X - exit the game, the current game state will be lost, the next time the game is
 * started, it will resume from the last unfinished level
 * 2. CTRL-S - exit the game, saves the game state, game will resume next time the
 * application will be started
 * 3. CTRL-R - resume a saved game -- this will pop up a file selector to select a saved game
 * to be loaded
 * 4. CTRL-1 - start a new game at level 1
 * 5. CTRL-2 - start a new game at level 2
 * 6. SPACE - pause the game and display a “game is paused” dialog
 * 7. ESC - close the “game is paused” dialog and resume the game
 * 8. UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
 * The application window should display the time left to play, the current level, keys collected, and
 * the number of treasures that still need to be collected. It should also offer buttons and menu
 * items to pause and exit the game, to save the game state and to resume a saved game, and to
 * display a help page with game rules.
 * Note that the actual drawing of the maze is not the responsibility of the application module,
 * but of the rendering module.
 * This module also manages a countdown -- each level has a maximum time associated with it
 * (level 1 -- 1 min), and once the countdown reaches zero, the game terminates with a message
 * informing the user, and then resetting the game to replay the current level.
 * The application package also includes an executable class
 * nz.ac.vuw.ecs.swen225.gp22.app.Main which starts the game.
 *
 * @author Kevin Zeng
 * ID: 300563468
 */
package nz.ac.vuw.ecs.swen225.gp22.app;
