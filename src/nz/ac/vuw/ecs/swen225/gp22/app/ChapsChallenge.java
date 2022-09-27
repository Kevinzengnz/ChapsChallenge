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
    Runnable closePhase = ()->System.exit(0);
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
        setPhase(Phase.level1(()->levelOne(),()->levelOne()));
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
            renderer.ping(p.model().player().getPoint(), p.model().entities(), new ArrayList<>());
            pings++;
            if(pings % 4 == 0) {
                p.model().ping();
            }
            renderer.repaint();
        }).start();

        JPanel viewport = new JPanel();
        viewport.setFocusable(true);
        setPreferredSize(getSize());//to keep the current size
        viewport.addKeyListener(p.controller());

        add(BorderLayout.CENTER,viewport);

        pack();                     //after pack
        viewport.requestFocus();
    }
}