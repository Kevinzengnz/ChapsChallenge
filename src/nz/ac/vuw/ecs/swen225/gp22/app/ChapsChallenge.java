package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Audio;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Chap's Challenge.
 *
 * @author Kevin Zeng
 * ID: 300563468
 */
public class ChapsChallenge extends JFrame {
  /**
   * Frames per second that the game should run in.
   */
  private static final int FRAME_RATE = 30;
  /**
   * KeyListener for UI controls.
   */
  private final GameController gameController;
  /**
   * Runs when game is closed.
   */
  private Runnable closePhase = () -> System.exit(0);
  /**
   * Number of frames ran from start of game.
   */
  private int pings;
  /**
   * Current phase of the game.
   */
  private Phase currentPhase;
  /**
   * Timer that performs certain actions every frame.
   */
  private Timer timer;

  /**
   * Boolean for whether the game is currently paused.
   */
  private boolean paused = false;

  /**
   * Boolean for whether the help dialogue is currently shown.
   */
  private boolean helpDialogue = false;

  /**
   * Creates a new instance of Chaps Challenge.
   */
  ChapsChallenge() {
    assert SwingUtilities.isEventDispatchThread();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(1366, 768);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        closePhase.run();
      }
    });
    gameController = new GameController(this);

    levelOne();
  }

  /**
   * Starts up level one.
   */
  public void levelOne() {
    setPhase(Phase.level1(this::levelOne, this::levelOne));
  }

  /**
   * Starts up level two.
   */
  public void levelTwo() {
    setPhase(Phase.level2(this::levelTwo, this::levelTwo));
  }

  /**
   * Loads a game from a file, which user chooses from file chooser.
   */
  public void loadGame() {
    JFileChooser fileChooser =
        new JFileChooser("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/");
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      String fileName = fileChooser.getSelectedFile().getPath();
      setPhase(Phase.loadLevel(fileName));
    }
  }

  /**
   * Pauses the game.
   */
  public void pauseGame() {
    currentPhase.controller().pause();
    paused = true;
    timer.stop();
  }

  /**
   * If the game is paused, unpauses it.
   */
  public void unPauseGame() {
    currentPhase.controller().pause();
    paused = false;
    timer.start();
  }

  /**
   * Saves the current state of the game, and exits.
   */
  public void saveAndExit() {
    saveGame();
    exitGame();
  }

  /**
   * Saves the current phase of the game to an xml file.
   */
  public void saveGame() {
    currentPhase.model().saveGame();
  }

  /**
   * Exits the game.
   */
  public void exitGame() {
    closePhase.run();
  }

  /**
   * Shows/hides the help dialogue.
   */
  public void showHelp() {
    if (!helpDialogue) {
      currentPhase.renderer().showPopup("Controls: WASD or arrow keys to control player, "
          + "ESC to pause, "
          + "SPACE to resume, "
          + "CTRL-1 to load level 1, "
          + "CTRL-2 to load level 2");
      helpDialogue = true;
    } else {
      currentPhase.renderer().hidePopup();
      helpDialogue = false;
    }
  }

  /**
   * Returns the current phase of the game.
   *
   * @return currentPhase
   */
  public Phase getPhase() {
    return currentPhase;
  }

  /**
   * Sets up the timer and the viewport.
   *
   * @param p Phase
   */
  private void setPhase(Phase p) {
    currentPhase = p;
    closePhase = () -> {
      p.model().recorder().endRecording();
      System.exit(0);
    };
    setVisible(true);

    if (timer != null) {
      timer.stop();
    }
    pings = 0;

    Renderer renderer = p.renderer();
    renderer.addKeyListener(p.controller());
    renderer.addKeyListener(gameController);

    renderer.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.FIRST_LINE_END;
    c.gridy = 0;

    //TODO: get level from model once implemented
    JLabel level = new JLabel("Level: ");
    level.setFont(new Font("Verdana", Font.PLAIN, 20));
    level.setFocusable(false);
    renderer.add(level, c);

    c.gridy = 1;
    JLabel treasuresLeft = new JLabel("Treasures left: " + p.model().treasuresLeft());
    treasuresLeft.setFont(new Font("Verdana", Font.PLAIN, 20));
    treasuresLeft.setFocusable(false);
    renderer.add(treasuresLeft, c);

    c.gridy = 2;
    JLabel timeLeft = new JLabel("Time Left: " + p.model().timeLeft());
    timeLeft.setFont(new Font("Verdana", Font.PLAIN, 20));
    timeLeft.setFocusable(false);
    renderer.add(timeLeft, c);
    p.model().entities().forEach(e -> e.setSoundEffect(Audio.getSoundPlayer(e.getSprite())));
    //Creates timer, so it runs in approximately 30 frames per second
    timer = new Timer(1000 / FRAME_RATE, unused -> {
      assert SwingUtilities.isEventDispatchThread();
      pings++;
      if (pings % 4 == 0) {
        p.model().ping();
      }
      renderer.ping(p.model().player().getPoint(),
          p.model().entities(),
          p.model().player().getKeys());
      renderer.repaint();
      treasuresLeft.setText("Treasures left: " + p.model().treasuresLeft());
      if (pings % FRAME_RATE == 0) {
        p.model().decrementTime();
        timeLeft.setText("Time Left: " + p.model().timeLeft());
      }
    });
    timer.start();

    //Initialises buttons
    var startRecording = new JButton("Start recording");
    var endRecording = new JButton("End recording");
    startRecording.addActionListener(e ->
        p.model().recorder().startRecording("default.xml", "level 1"));
    endRecording.addActionListener(e ->
        p.model().recorder().endRecording());
    startRecording.setFocusable(false);
    endRecording.setFocusable(false);

    var pauseBtn = new JButton("Pause");
    pauseBtn.addActionListener(e -> {
      if (!paused) {
        pauseGame();
        pauseBtn.setText("Resume");
      } else {
        unPauseGame();
        pauseBtn.setText("Pause");
      }
    });
    pauseBtn.setFocusable(false);

    var exitBtn = new JButton("Exit game");
    exitBtn.addActionListener(e -> exitGame());
    exitBtn.setFocusable(false);

    var saveBtn = new JButton("Save game");
    saveBtn.addActionListener(e -> saveGame());
    saveBtn.setFocusable(false);

    var loadBtn = new JButton("Load game");
    loadBtn.addActionListener(e -> loadGame());
    loadBtn.setFocusable(false);

    var helpBtn = new JButton("Show/Hide Help");
    helpBtn.addActionListener(e -> showHelp());
    helpBtn.setFocusable(false);

    var startStopReplay = new JButton("Start/Stop Replay");
    helpBtn.addActionListener(e -> currentPhase.model());
    helpBtn.setFocusable(false);

    c.gridx = 5;
    c.weightx = 0.5;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LAST_LINE_END;

    //adds buttons to renderer
    renderer.add(startRecording, c);
    c.gridy = 1;
    renderer.add(endRecording, c);
    c.gridy = 2;
    renderer.add(pauseBtn, c);
    c.gridy = 3;
    renderer.add(helpBtn, c);
    c.gridy = 4;
    renderer.add(saveBtn, c);
    c.gridy = 5;
    renderer.add(loadBtn, c);
    c.gridy = 6;
    renderer.add(exitBtn, c);


    add(BorderLayout.CENTER, renderer);
    renderer.setFocusable(true);
    setPreferredSize(getSize()); //to keep the current size
    pack();                     //after pack
    renderer.requestFocus();
  }

}
