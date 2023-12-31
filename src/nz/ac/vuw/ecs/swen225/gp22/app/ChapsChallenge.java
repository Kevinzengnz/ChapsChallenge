package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.Replay;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Audio;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;


/**
 * Chap's Challenge.
 *
 * @author Kevin Zeng
 *         ID: 300563468
 */
public class ChapsChallenge extends JFrame {
  /**
   * Frames per second that the game should run in.
   */
  private static final int FRAME_RATE = 30;

  /**
   * Speed for replays.
   */
  private int clockSpeed = 4;

  /**
   * KeyListener for UI controls.
   */
  private final GameController gameController;
  /**
   * Runs when game is closed.
   */
  private Runnable closePhase = () -> {
  };
  /**
   * Number of frames ran from start of game.
   */
  private int pings;
  /**
   * Current phase of the game.
   */
  private Phase currentPhase;

  /**
   * A default timer object that performs no actions.
   */
  private static final Timer EMPTY_TIMER = new Timer(0, e -> {});

  /**
   * Timer that performs certain actions every frame.
   */
  private Timer timer = EMPTY_TIMER;

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
        System.exit(0);
      }
    });
    gameController = new GameController(this);

    levelStartMenu();
    setVisible(true);
  }

  /**
   * Starts up the start menu.
   */
  public void levelStartMenu() {
    setBackground(Color.BLACK);
    JPanel centerPanel = new JPanel();
    centerPanel.setBackground(Color.BLACK);
    centerPanel.setLayout(new GridBagLayout());
    var welcome = new Label("Chap's challenge."
        + "Press a button to load a level.");
    centerPanel.add(welcome);
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.BLACK);
    addKeyListener(gameController);
    closePhase.run();
    closePhase = () -> {
      remove(centerPanel);
      remove(bottomPanel);
    };

    var levelOne = new Button("Level 1", e -> levelOne());
    var levelTwo = new Button("Level 2", e -> levelTwo());
    var exitBtn = new Button("Exit", e -> exitGame());
    var loadBtn = new Button("Load game", e -> loadGame());
    bottomPanel.add(levelOne);
    bottomPanel.add(levelTwo);
    bottomPanel.add(loadBtn);
    bottomPanel.add(exitBtn);
    add(BorderLayout.CENTER, centerPanel);
    add(BorderLayout.SOUTH, bottomPanel);

    setPreferredSize(getSize());
    pack();
  }

  /**
   * Starts up level one.
   */
  public void levelOne() {
    setPhase(Phase.level1(this::victory, this::gameOver));
  }

  /**
   * Starts up level two.
   */
  public void levelTwo() {
    setPhase(Phase.level2(this::victory, this::gameOver));
  }

  /**
   * Loads a game from a file, which user chooses from file chooser.
   */
  public void loadGame() {
    JFileChooser fileChooser =
        new JFileChooser("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/");
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      String fileName = fileChooser.getSelectedFile().getPath();
      setPhase(Phase.loadLevel(fileName, this::victory, this::gameOver));
      unPauseGame();
      setClockSpeed(4);
    }
  }

  /**
   * Victory screen.
   * Displays a popup over the renderer, and pauses the game.
   * User can resume afterwards by pressing a load keybinding, or pressing a button.
   */
  public void victory() {
    currentPhase.renderer()
        .showPopup("You completed the level! Click a button to load a new level.");
    currentPhase.renderer().removeKeyListener(currentPhase.controller());
    timer.stop();
    timer = EMPTY_TIMER;
    helpDialogue = true;
  }

  /**
   * Game over screen.
   * Displays a popup over the renderer, and pauses the game.
   * User can resume afterwards by pressing a load keybinding, or pressing a button.
   */
  public void gameOver() {
    currentPhase.renderer().showPopup("You Died! Click a button to load a new level.");
    currentPhase.renderer().removeKeyListener(currentPhase.controller());
    timer.stop();
    timer = EMPTY_TIMER;
    helpDialogue = true;
  }

  /**
   * Pauses the game.
   */
  public void pauseGame() {
    currentPhase.controller().pause();
    timer.stop();
  }

  /**
   * If the game is paused, unpauses it.
   */
  public void unPauseGame() {
    currentPhase.controller().unPause();
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
    System.exit(0);
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
          + "CTRL-2 to load level 2. Use buttons on right side of screen"
          + "for other commands. Press show/hide help to hide this popup.");
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
    JPanel infoPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    currentPhase = p;
    closePhase.run(); //close phase before adding any element of the new phase
    closePhase = () -> {
      p.model().recorder().endRecording();
      helpDialogue = false;
      remove(p.renderer());
      remove(infoPanel);
      remove(buttonsPanel);
    };
    setVisible(true);

    timer.stop();
    pings = 0;

    Renderer renderer = p.renderer();
    renderer.addKeyListener(p.controller());
    renderer.addKeyListener(gameController);

    infoPanel.setBackground(Color.BLACK);
    infoPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.CENTER;
    c.gridy = 0;

    Label level = new Label("Level: " + p.model().levelNumber());
    infoPanel.add(level, c);

    c.gridy = 1;
    Label treasuresLeft = new Label("Treasures left: " + p.model().treasuresLeft());
    infoPanel.add(treasuresLeft, c);

    c.gridy = 2;
    Label timeLeft = new Label("Time Left: " + p.model().timeLeft());
    infoPanel.add(timeLeft, c);

    p.model().entities().forEach(e -> e.setSoundEffect(Audio.getSoundPlayer(e.getSpriteName())));
    //Creates timer, so it runs in approximately 30 frames per second
    timer = new Timer(1000 / FRAME_RATE, unused -> {
      assert SwingUtilities.isEventDispatchThread();
      pings++;
      if (pings % clockSpeed == 0) {
        p.model().ping();
      }
      renderer.ping(p.model().player().getPoint(),
          p.model().entities(),
          p.model().player().getKeys());
      renderer.repaint();
      treasuresLeft.setText("Treasures left: " + p.model().treasuresLeft());
      if (pings % (int) (FRAME_RATE / (4.0 / clockSpeed)) == 0) {
        p.model().decrementTime();
        timeLeft.setText("Time Left: " + p.model().timeLeft());
      }
    });
    timer.start();

    c.gridx = 5;
    c.gridy = 0;
    c.weightx = 0.5;
    c.weighty = 0.;
    c.anchor = GridBagConstraints.LINE_END;
    buttonsPanel.setLayout(new GridBagLayout());
    buttonsPanel.setBackground(Color.BLACK);

    //Initialises and adds buttons

    var startRecording = new Button("Start recording", e -> startRecording());
    buttonsPanel.add(startRecording, c);

    var endRecording = new Button("End recording", e -> endRecording());
    c.gridy = 1;
    buttonsPanel.add(endRecording, c);

    var pauseBtn = new Button("Pause", e -> pauseGame());
    c.gridy = 2;
    buttonsPanel.add(pauseBtn, c);

    var resumeBtn = new Button("Resume", e -> unPauseGame());
    c.gridy = 3;
    buttonsPanel.add(resumeBtn, c);

    var helpBtn = new Button("Show/Hide Help", e -> showHelp());
    c.gridy = 4;
    buttonsPanel.add(helpBtn, c);

    var saveBtn = new Button("Save game", e -> saveGame());
    c.gridy = 5;
    buttonsPanel.add(saveBtn, c);

    var loadBtn = new Button("Load game", e -> loadGame());
    c.gridy = 6;
    buttonsPanel.add(loadBtn, c);

    var exitBtn = new Button("Exit game", e -> exitGame());
    c.gridy = 7;
    buttonsPanel.add(exitBtn, c);

    var loadReplay = new Button("Load Replay", e -> loadReplay());
    c.gridy = 8;
    buttonsPanel.add(loadReplay, c);

    var replayAutoplay = new Button("Autoplay Replay", e -> replayAutoplay());
    c.gridy = 9;
    buttonsPanel.add(replayAutoplay, c);

    var replayAutopause = new Button("Stop Autoplay Replay", e -> replayAutopause());
    c.gridy = 10;
    buttonsPanel.add(replayAutopause, c);

    var replayNextTick = new Button("Next tick of Replay", e -> replayNextTick());
    c.gridy = 11;
    buttonsPanel.add(replayNextTick, c);

    var increaseReplaySpeed = new Button("Increase replay speed", e -> increaseReplaySpeed());
    c.gridy = 12;
    buttonsPanel.add(increaseReplaySpeed, c);

    var decreaseReplaySpeed = new Button("Decrease replay speed", e -> decreaseReplaySpeed());
    c.gridy = 13;
    buttonsPanel.add(decreaseReplaySpeed, c);

    add(BorderLayout.CENTER, renderer);
    add(BorderLayout.EAST, buttonsPanel);
    add(BorderLayout.WEST, infoPanel);
    buttonsPanel.setFocusable(false);
    infoPanel.setFocusable(false);

    renderer.setFocusable(true);
    setPreferredSize(getSize()); //to keep the current size
    pack();                     //after pack
    renderer.requestFocus();
  }

  /**
   * Starts the recording in the model.
   */
  public void startRecording() {
    currentPhase.model().recorder()
        .startRecording(currentPhase.model(), "replay "
            + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new java.util.Date()));
  }

  /**
   * Ends the recording in the model.
   */
  public void endRecording() {
    currentPhase.model().recorder().endRecording();
  }

  /**
   * Loads a replay from a file, which user chooses from file chooser.
   */
  public void loadReplay() {
    JFileChooser fileChooser =
        new JFileChooser("Replays/");
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      String fileName = fileChooser.getSelectedFile().getPath();
      try {
        Replay.loadReplay(fileName, this);
      } catch (IOException e) {
        System.out.println(e.getMessage());
        return;
      }
      setPhase(Phase.loadLevel(Replay.getTiles(), Replay.getTimeLeft(), Replay.getLevelNumber(),
          this::victory, this::gameOver));
      pauseGame();
    }
  }

  /**
   * Sets the loaded replay to autoplay.
   */
  public void replayAutoplay() {
    Replay.autoPlay();
  }

  /**
   * Pauses autoplay on the loaded replay.
   */
  public void replayAutopause() {
    Replay.autoPause();
  }

  /**
   * Move to the next tick of the game clock in the loaded replay.
   */
  public void replayNextTick() {
    Replay.nextTick();
  }

  /**
   * Gets the clock speed.
   *
   * @return clock speed of the game.
   */
  public int getClockSpeed() {
    return clockSpeed;
  }

  /**
   * Sets the clock speed.
   *
   * @param c clock speed to be set to
   */
  public void setClockSpeed(int c) {
    this.clockSpeed = c;
  }

  /**
   * Increases the speed of the replay
   */
  public void increaseReplaySpeed() {
    Replay.increaseSpeed();
  }

  /**
   * Decreases the speed of the replay
   */
  public void decreaseReplaySpeed() {
    Replay.decreaseSpeed();
  }
}
