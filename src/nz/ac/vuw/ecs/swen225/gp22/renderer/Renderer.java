package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Point;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

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

    private List<Animation> animations = new ArrayList<>();

    public Renderer() {
    }

    /**
     * Updates the renderer with what to draw.
     */
    public void update(int camX, int camY, List<Entity> allEntities, List<Key> inventory) {
        cameraX = camX;
        cameraY = camY;
        this.inventory = inventory;
        animations.forEach(Animation::update);
        animations = animations.stream().filter(Animation::isRunning).toList();
        entities = new ArrayList<>();
        entities = allEntities.stream()
                .filter(this::isEntityVisible)
                .filter(e -> animations.stream().noneMatch(a -> a.getEntity() == e))
                .sorted(Comparator.comparingInt(Entity::getDepth))
                .toList();

    }

    boolean isEntityVisible(Entity entity) {
        return entity.getPoint().x() >= cameraX - visionDistance && entity.getPoint().x() <= cameraX + visionDistance
                && entity.getPoint().y() >= cameraY - visionDistance && entity.getPoint().y() <= cameraY + visionDistance;
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
        drawAnimations(g, cellsLeft, cellsTop);
        drawInventory(g, cellsTop);
    }

    public void drawEntities(Graphics g, int left, int top) {
        for (Entity entity : entities) {
            Sprite sprite = entity.getSprite();
            Point screenPos = worldToScreen(entity.getPoint());
            g.drawImage(sprite.image, left + screenPos.x(), top + screenPos.y(), null);
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

    public void drawAnimations(Graphics g, int left, int top) {
        for (Animation anim : animations) {
            g.drawImage(anim.getSprite().image, left + anim.getX(), top + anim.getY(), null);
        }
    }

    /**
     * Converts a tile coordinate into a point for rendering
     * @param point
     * @return
     */
    public Point worldToScreen(Point point) {
        return new Point((point.x() - cameraX + visionDistance) * tileSize, (point.y() - cameraY + visionDistance) * tileSize);
    }

    public void addAnimation(Animation animation) {
        if (animations.stream().anyMatch(a -> a.getEntity().equals(animation.getEntity()))) throw new IllegalArgumentException("Entity already has an animation");
        animations = new ArrayList<>(animations);
        animations.add(animation);
    }

    record Key(Sprite sprite, int amount) { }



}

