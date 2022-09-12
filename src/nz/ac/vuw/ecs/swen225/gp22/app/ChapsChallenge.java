package nz.ac.vuw.ecs.swen225.gp22.app;


import nz.ac.vuw.ecs.swen225.gp22.renderer.Renderer;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Chap's Challenge
 * @author Kevin Zeng
 * ID: 300563468
 */
public class ChapsChallenge extends JFrame{
    ChapsChallenge() {
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Renderer renderer = new Renderer();
        List<Renderer.Entity> entities = new ArrayList<>();
        for (int i=0;i<20;i++) {
            for (int j=0;j<20;j++) {
                if (i == 0 || j == 0 || i==19 || j == 19) {
                    entities.add(new Renderer.Entity(Sprite.WALL, new Point(i, j), 0));
                }
                else {
                    entities.add(new Renderer.Entity(Sprite.FLOOR, new Point(i, j), 0));
                }
            }
        }

        Renderer.Entity p = new Renderer.Entity(Sprite.PLAYER_DOWN, new Point(3, 4), 2);
        entities.add(p);
        renderer.update(p.pos().x, p.pos().y, entities, new ArrayList<>());
        add(renderer);
        setSize(1366, 768);
        setVisible(true);

    }
}