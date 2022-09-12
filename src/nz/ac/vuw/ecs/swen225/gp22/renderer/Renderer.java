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

    private final int inventorySize = 4;
    List<Key> inventory = new ArrayList<>();

    int cameraX = 0;
    int cameraY = 0;
    private Sprite[][] tiles = new Sprite[9][9];   // Tiles which are within sight of the camera
    private List<Actor> actors = new ArrayList<>(); // Actors which are within sight of the camera

    public Renderer() {
    }

    /**
     * Updates the renderer with what to draw.
     */
    public void update(int camX, int camY, Tile[][] allTiles, List<Actor> allActors, List<Key> inventory) {
        cameraX = camX;
        cameraY = camY;
        this.inventory = inventory;
        for (int i=cameraX-visionDistance;i<cameraX + visionDistance+1;i++) {
            for (int j=cameraY-visionDistance;j<cameraY + visionDistance+1;j++) {
                int row = j-cameraY+visionDistance;
                int col = i-cameraX+visionDistance;
                if (i < 0 || j < 0 || j > allTiles.length || i > allTiles[0].length)  {
                    tiles[row][col] = null;
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
        return actor.pos.x >= cameraX - visionDistance && actor.pos.x <= cameraX + visionDistance
                && actor.pos.y >= cameraY - visionDistance && actor.pos.y <= cameraY + visionDistance;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        final int cellsLeft = (getWidth() - visionSize * tileSize) / 2;
        final int cellsTop = Math.min((getHeight() - visionSize * tileSize) / 2, 25);
        g.setColor(Color.WHITE);
        g.drawRect(cellsLeft-1, cellsTop-1, visionSize * tileSize+1, visionSize * tileSize+1);
        drawTiles(g, cellsLeft, cellsTop);
        drawActors(g, cellsLeft, cellsTop);
        drawInventory(g, cellsTop);
    }

    public void drawTiles(Graphics g, int left, int top) {
        for (int i=0;i<visionSize;i++) {
            for (int j=0;j<visionSize;j++) {
                Sprite sprite = tiles[j][i];
                if (sprite == null) continue;
                g.drawImage(sprite.image, left + i * tileSize, top + j * tileSize, null);
            }
        }
    }

    public void drawActors(Graphics g, int left, int top) {
        for (Actor actor : actors) {
            Sprite sprite = actor.sprite;
            Point screenPos = worldToScreen(actor.pos);
            g.drawImage(sprite.image, left + screenPos.x, top + screenPos.y, null);
        }
    }

    public void drawInventory(Graphics g, int cellsTop) {
        final int left = (getWidth() - inventorySize * tileSize) / 2;
        final int top = (getHeight() + (cellsTop + tileSize * visionSize) - tileSize) / 2;
        for (int i=0;i<inventorySize;i++) {
            g.drawRect(left + i * tileSize, top, tileSize, tileSize);
            if (i < inventory.size()) {
                Key key = inventory.get(i);
                g.drawImage(key.sprite.image, left + i * tileSize, top, null);
                if (key.amount > 1) {
                    g.drawImage(Sprite.UI_TWO.image,
                            left + i * tileSize + tileSize-Sprite.UI_TWO.image.getWidth(),
                            top, null);
                }
            }
        }
    }

    public Point worldToScreen(Point point) {
        return new Point((point.x - cameraX + visionDistance) * tileSize, (point.y - cameraY + visionDistance) * tileSize);
    }

    // temp (until actual classes are completed)
    public static class Tile {
        public Sprite sprite = Sprite.FLOOR;
    }
    public static class Actor {
        public Point pos;
        public Sprite sprite = Sprite.PLAYER_DOWN;
    }

    record Key(Sprite sprite, int amount) { }
}

