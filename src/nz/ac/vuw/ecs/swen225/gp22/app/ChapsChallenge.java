package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.recorder.Replay;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Audio;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

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
   * Timer that performs certain actions every frame.
   */
  private Timer timer;

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

  public void levelStartMenu() {
    var welcome=new Label("Chap's challenge. ");
    JPanel bottomPanel = new JPanel();
    addKeyListener(gameController);
    closePhase.run();
    closePhase=()->{
      remove(welcome);
      remove(bottomPanel);
    };

    var levelOne =new Button("Level 1", e->levelOne());
    var levelTwo =new Button("Level 2", e->levelTwo());
    var exitBtn = new Button("Exit", e -> exitGame());
    var loadBtn = new Button("Load game", e -> loadGame());
    bottomPanel.add(levelOne);
    bottomPanel.add(levelTwo);
    bottomPanel.add(loadBtn);
    bottomPanel.add(exitBtn);
    add(BorderLayout.CENTER,welcome);
    add(BorderLayout.SOUTH,bottomPanel);

    setPreferredSize(getSize());
    pack();
  }

  /**
   * Starts up level one.
   */
  public void levelOne() {
    setPhase(Phase.level1(this::levelOne, this::gameOver));
  }

  /**
   * Starts up level two.
   */
  public void levelTwo() {
    setPhase(Phase.level2(this::levelTwo, this::gameOver));
  }

  /**
   * Game over screen.
   * Displays a popup over the renderer, and pauses the game.
   * User can resume afterwards by pressing a load keybinding, or pressing a button.
   */
  public void gameOver() {
    currentPhase.renderer().showPopup("You Died! Click a button to load a new level.");
    currentPhase.renderer().removeKeyListener(currentPhase.controller());
    pauseGame();
    helpDialogue = true;
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
    closePhase.run();//close phase before adding any element of the new phase
    closePhase = () -> p.model().recorder().endRecording();
    setVisible(true);

    if (timer != null) {
      timer.stop();
    }
    pings = 0;

    Renderer renderer = p.renderer();
    renderer.addKeyListener(p.controller());
    renderer.addKeyListener(gameController);

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.FIRST_LINE_END;
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
    var startRecording = new Button("Start recording", e -> startRecording());
    var endRecording = new Button("End recording", e -> endRecording());
    var pauseBtn = new Button("Pause", e -> pauseGame());
    var resumeBtn = new Button("Resume", e -> unPauseGame());
    var exitBtn = new Button("Exit game", e -> exitGame());
    var saveBtn = new Button("Save game", e -> saveGame());
    var loadBtn = new Button("Load game", e -> loadGame());
    var helpBtn = new Button("Show/Hide Help", e -> showHelp());
    var loadReplay = new Button("Load Replay", e -> loadReplay());
    var replayAutoplay = new Button("Autoplay Replay", e -> replayAutoplay());
    var replayAutopause = new Button("Stop Autoplay Replay", e -> replayAutopause());
    var replayNextTick = new Button("Next tick of Replay", e -> replayNextTick());

    c.gridx = 5;
    c.weightx = 0.5;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LAST_LINE_END;

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridBagLayout());

    //adds buttons to buttonsPanel
    buttonsPanel.add(startRecording, c);
    c.gridy = 1;
    buttonsPanel.add(endRecording, c);
    c.gridy = 2;
    buttonsPanel.add(pauseBtn, c);
    c.gridy = 3;
    buttonsPanel.add(resumeBtn, c);
    c.gridy = 4;
    buttonsPanel.add(helpBtn, c);
    c.gridy = 5;
    buttonsPanel.add(saveBtn, c);
    c.gridy = 6;
    buttonsPanel.add(loadBtn, c);
    c.gridy = 7;
    buttonsPanel.add(exitBtn, c);

    c.gridy = 8;
    buttonsPanel.add(loadReplay, c);
    c.gridy = 9;
    buttonsPanel.add(replayAutoplay, c);
    c.gridy = 10;
    buttonsPanel.add(replayAutopause, c);
    c.gridy = 11;
    buttonsPanel.add(replayNextTick, c);

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
        .startRecording(currentPhase.model(), "replay " +
            new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
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
      Replay.loadReplay(fileName, this);
      setPhase(Phase.loadLevel(Replay.getTiles(),Replay.getTimeLeft(),Replay.getLevelNumber()));
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
}
