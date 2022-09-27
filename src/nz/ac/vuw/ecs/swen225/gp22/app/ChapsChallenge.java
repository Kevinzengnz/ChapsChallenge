package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Chap's Challenge
 * @author Kevin Zeng
 * ID: 300563468
 */
public class ChapsChallenge extends JFrame{
    int timeLeft;

    /**
     * Creates a new instance of Chaps Challenge
     */
    ChapsChallenge() {
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1366, 768);
        setVisible(true);
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

        //Creates timer, so it runs in approximately 30 frames per second
        new Timer(34,unused->{
            assert SwingUtilities.isEventDispatchThread();
            p.model().ping(renderer);
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