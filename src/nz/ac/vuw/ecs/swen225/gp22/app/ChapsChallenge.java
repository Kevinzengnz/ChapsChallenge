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
    Runnable closePhase = ()-> System.exit(0);

    int pings;

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

        levelOne();
    }

    /**
     * Starts up level one
     */
    private void levelOne() {
        setPhase(Phase.level1(this::levelOne, this::levelOne));
    }

    /**
     * Sets up the timer and the viewport
     * @param p Phase
     */
    void setPhase(Phase p){
        Renderer renderer = p.renderer();
        add(renderer);
        setVisible(true);

        //Creates timer, so it runs in approximately 30 frames per second
        new Timer(34,unused->{
            assert SwingUtilities.isEventDispatchThread();
            pings++;
            if(pings % 4 == 0) {
                p.model().ping();
            }
            renderer.ping(p.model().player().getPoint(), p.model().entities(), new ArrayList<>());
            renderer.repaint();
        }).start();

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
        add(BorderLayout.CENTER,renderer);
        add(BorderLayout.WEST,startRecording);
        add(BorderLayout.EAST,endRecording);
        pack();                     //after pack
        renderer.requestFocus();
    }
}