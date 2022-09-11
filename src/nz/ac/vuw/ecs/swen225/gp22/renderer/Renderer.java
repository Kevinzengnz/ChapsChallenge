package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer extends JPanel {
    // Drawing constants
    private final int visionSize = 9;
    private final int visionDistance = (visionSize-1)/2;
    private final int tileSize = 64;
    final int left = (getWidth() - visionSize * tileSize) / 2;
    final int top = (getHeight() - visionSize * tileSize) / 2;

    int cameraX = 0;
    int cameraY = 0;
    private Sprite[][] tiles = new Sprite[9][9];   // Tiles which are within sight of the camera
    private List<Actor> actors = new ArrayList<>(); // Actors which are within sight of the camera

    /**
     * Updates the renderer with what to draw.
     */
    public void update(int camX, int camY, Tile[][] allTiles, List<Actor> allActors) {
        cameraX = camX;
        cameraY = camY;
        for (int i=camX-visionDistance;i<cameraX + visionDistance;i++) {
            for (int j=cameraY-visionDistance;j<cameraY + visionDistance;j++) {
                int row = j-cameraX+visionDistance;
                int col = i-cameraY-visionDistance;
                if (i < 0 || j < 0 || j > allTiles.length || i > allTiles[0].length)  {
                    tiles[row][col] = Sprite.FLOOR;
                    continue;
                }
                tiles[row][col] = allTiles[j][i].sprite;
            }
        }
        actors.clear();
        for (Actor actor : allActors) {
            if (isActorVisible(actor)) actors.add(actor);
        }
    }

    boolean isActorVisible(Actor actor) {
        return actor.pos.x < cameraX - visionDistance && actor.pos.x > cameraX + visionDistance
                && actor.pos.y < cameraY - visionDistance && actor.pos.y > cameraY + visionDistance;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        drawTiles(g);
        drawActors(g);
    }

    public void drawTiles(Graphics g) {
        for (int i=0;i<visionSize-1;i++) {
            for (int j=0;j<visionSize-1;j++) {
                Sprite sprite = tiles[j][i];
                g.drawImage(sprite.image, left + i * tileSize, top + i * tileSize, null);
            }
        }
    }

    public void drawActors(Graphics g) {
        for (Actor actor : actors) {
            Sprite sprite = actor.sprite;
            Point screenPos = worldToScreen(actor.pos);
            g.drawImage(sprite.image, (int) (left + screenPos.x), (int) (top + screenPos.y), null);
        }
    }

    public Point worldToScreen(Point point) {
        return new Point(point.x - cameraX, point.y - cameraY);
    }

    // temp (until actual classes are completed)
    class Tile {
        public Sprite sprite = Sprite.FLOOR;
    }
    class Actor {
        public Point pos;
        public Sprite sprite = Sprite.PLAYER;
    }
    record Point(float x, float y) {}
}

