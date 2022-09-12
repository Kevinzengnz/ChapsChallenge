package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
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
    // Entities currently visible
    private List<Entity> entities = new ArrayList<>();

    public Renderer() {
    }

    /**
     * Updates the renderer with what to draw.
     */
    public void update(int camX, int camY, List<Entity> allEntities, List<Key> inventory) {
        cameraX = camX;
        cameraY = camY;
        this.inventory = inventory;
        entities.clear();
        entities = allEntities.stream()
                .filter(this::isEntityVisible)
                .sorted(Comparator.comparingInt((Entity e) -> e.depth))
                .toList();
    }

    boolean isEntityVisible(Entity entity) {
        return entity.pos.x >= cameraX - visionDistance && entity.pos.x <= cameraX + visionDistance
                && entity.pos.y >= cameraY - visionDistance && entity.pos.y <= cameraY + visionDistance;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        final int cellsLeft = (getWidth() - visionSize * tileSize) / 2;
        final int cellsTop = Math.min((getHeight() - visionSize * tileSize) / 2, 25);
        g.setColor(Color.WHITE);
        g.drawRect(cellsLeft-1, cellsTop-1, visionSize * tileSize+1, visionSize * tileSize+1);
        drawEntities(g, cellsLeft, cellsTop);
        drawInventory(g, cellsTop);
    }

    public void drawEntities(Graphics g, int left, int top) {
        for (Entity entity : entities) {
            Sprite sprite = entity.sprite;
            Point screenPos = worldToScreen(entity.pos);
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

    public record Entity(Sprite sprite, Point pos, int depth) {
    }

    record Key(Sprite sprite, int amount) { }
}

