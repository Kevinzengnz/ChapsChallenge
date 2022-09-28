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
    private Runnable closePhase = ()-> System.exit(0);
    private int pings; //number of frames ran from start of game
    private Phase currentPhase;
    private GameController gameController;
    private Timer timer;

    /**
     * Creates a new instance of Chaps Challenge
     */
    ChapsChallenge() {
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1366, 768);
        addWindowListener(new WindowAdapter(){
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
        add(renderer);
        setVisible(true);

        //Creates timer, so it runs in approximately 30 frames per second
        timer = new Timer(34,unused->{
            assert SwingUtilities.isEventDispatchThread();
            pings++;
            if(pings % 4 == 0) {
                p.model().ping();
            }
            renderer.ping(p.model().player().getPoint(), p.model().entities(), new ArrayList<>());
            renderer.repaint();
        });
        timer.start();

        closePhase = ()->{
            p.model().recorder().endRecording();
            System.exit(0);
        };

        var startRecording=new JButton("Start recording");
        var endRecording=new JButton("End recording");

        startRecording.addActionListener(e -> p.model().recorder().startRecording("default.xml","level 1"));
        endRecording.addActionListener(e -> p.model().recorder().endRecording());
        startRecording.setFocusable(false);
        endRecording.setFocusable(false);

        renderer.setFocusable(true);
        setPreferredSize(getSize());//to keep the current size

        renderer.addKeyListener(p.controller());
        renderer.addKeyListener(gameController);

        add(BorderLayout.CENTER,renderer);
        add(BorderLayout.WEST,startRecording);
        add(BorderLayout.EAST,endRecording);
        pack();                     //after pack
        renderer.requestFocus();
    }

    public void pauseGame() {
        timer.stop();
    }

    public void unPauseGame() {
        timer.start();
    }

    public void saveAndExit() {
        saveGame();
        exitGame();
    }

    public void saveGame() {
        currentPhase.model().saveGame();
    }

    public void exitGame() {
        closePhase.run();
    }

}