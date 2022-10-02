package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Chap's Challenge
 * @author Kevin Zeng
 * ID: 300563468
 */
public class ChapsChallenge extends JFrame{
    private Runnable closePhase = () -> System.exit(0);
    private int pings; //number of frames ran from start of game
    private Phase currentPhase;
    private final GameController gameController;
    private Timer timer;
    private boolean paused = false;

    /**
     * Creates a new instance of Chaps Challenge
     */
    ChapsChallenge() {
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1366, 768);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){closePhase.run();} });
        gameController = new GameController(this);

        levelOne();
    }

    /**
     * Starts up level one
     */
    public void levelOne() {
        setPhase(Phase.level1(this::levelOne, this::levelOne));
    }

    /**
     * Starts up level two
     */
    public void levelTwo() {
        setPhase(Phase.level2(this::levelTwo, this::levelTwo));
    }

    /**
     * Sets up the timer and the viewport
     * @param p Phase
     */
    private void setPhase(Phase p){
        currentPhase = p;
        Renderer renderer = p.renderer();
        setVisible(true);

        if(timer != null) timer.stop();
        pings = 0;

        //Creates timer, so it runs in approximately 30 frames per second
        timer = new Timer(34, unused -> {
            assert SwingUtilities.isEventDispatchThread();
            pings++;
            if(pings % 4 == 0) {
                p.model().ping();
            }
            renderer.ping(p.model().player().getPoint(), p.model().entities(), new ArrayList<>());
            renderer.repaint();
        });
        timer.start();

        closePhase = () -> {
            p.model().recorder().endRecording();
            System.exit(0);
        };

        //Initialises buttons
        var startRecording = new JButton("Start recording");
        var endRecording = new JButton("End recording");
        startRecording.addActionListener(e -> p.model().recorder().startRecording("default.xml", "level 1"));
        endRecording.addActionListener(e -> p.model().recorder().endRecording());
        startRecording.setFocusable(false);
        endRecording.setFocusable(false);

        var pauseBtn = new JButton("Pause");

        pauseBtn.addActionListener(e -> {
            if(!paused) {
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

        renderer.setFocusable(true);
        setPreferredSize(getSize()); //to keep the current size

        renderer.addKeyListener(p.controller());
        renderer.addKeyListener(gameController);

        //adds buttons to renderer
        renderer.add(startRecording);
        renderer.add(endRecording);
        renderer.add(pauseBtn);
        renderer.add(exitBtn);
        renderer.add(saveBtn);
        renderer.add(loadBtn);

        add(BorderLayout.CENTER, renderer);
        pack();                     //after pack
        renderer.requestFocus();
    }

    /**
     * Loads a game from a file, which user chooses from file chooser
     */
    public void loadGame() {
        JFileChooser fileChooser = new JFileChooser("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/");
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getPath();
            setPhase(Phase.loadLevel(fileName));
        }
    }

    /**
     * Pauses the game
     */
    public void pauseGame() {
        paused = true;
        timer.stop();
    }

    /**
     * If the game is paused, unpauses it
     */
    public void unPauseGame() {
        paused = false;
        timer.start();
    }

    /**
     * Saves the current state of the game, and exits
     */
    public void saveAndExit() {
        saveGame();
        exitGame();
    }

    /**
     * Saves the current phase of the game to an xml file
     */
    public void saveGame() {
        currentPhase.model().saveGame();
    }

    /**
     * Exits the game
     */
    public void exitGame() {
        closePhase.run();
    }

}
