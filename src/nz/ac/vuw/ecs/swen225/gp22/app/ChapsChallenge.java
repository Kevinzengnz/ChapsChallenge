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
        levelOne();
    }

    /**
     * Starts up level one
     */
    private void levelOne() {
        Renderer renderer = new Renderer();
        List<Entity> entities = new ArrayList<>();
        for (int i=0;i<20;i++) {
            for (int j=0;j<20;j++) {
                if (i == 0 || j == 0 || i==19 || j == 19) {
                    entities.add(new Tile(Sprite.WALL, new Point(i, j)));
                }
                else {
                    entities.add(new Tile(Sprite.FLOOR, new Point(i, j)));
                }
            }
        }
        Player p = new Player(new Point(3, 4));
        entities.add(p);
        renderer.update(p.getPoint().x(), p.getPoint().y(), entities, new ArrayList<>());
        add(renderer);
        setSize(1366, 768);
        setVisible(true);

        //Creates timer, so it runs in approximately 30 frames per second
        new Timer(34,unused->{
            assert SwingUtilities.isEventDispatchThread();
            renderer.update(p.getPoint().x(), p.getPoint().y(), entities, new ArrayList<>());
            renderer.repaint();
        }).start();

        Controller controller = new Controller(p);
        JPanel viewport = new JPanel();
        viewport.setFocusable(true);
        setPreferredSize(getSize());//to keep the current size
        viewport.addKeyListener(controller);
        add(BorderLayout.CENTER,viewport);
        pack();                     //after pack
        viewport.requestFocus();
    }
}