package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Renderer extends JPanel {

    private final Camera camera;
    // Drawing constants
    private final int tileSize = 64;

    private final int inventorySize = 4;
    Map<Key, Integer> inventory = new HashMap<>();

    // Entities currently visible
    private List<Entity> entities = new ArrayList<>();

    private List<Animation> animations = new ArrayList<>();

    public Renderer() {
        camera = new Camera(5, 5);
    }

    /**
     * Updates the renderer with what to draw.
     */
    public void update(int camX, int camY, List<Entity> allEntities, List<Key> keys) {
        camera.update(camX, camY);
        inventory.clear();
        for (Key key : keys) {
            if (inventory.containsKey(key)) inventory.put(key, inventory.get(key)+1);
            else inventory.put(key, 1);
        }
        animations.forEach(Animation::update);
        endAnimations(animations.stream().filter(Animation::isFinished).toList());
        entities = new ArrayList<>();
        entities = allEntities.stream()
                .filter(this::isEntityVisible)
                .filter(e -> animations.stream().noneMatch(a -> a.getEntity() == e))
                .sorted(Comparator.comparingInt(Entity::getDepth))
                .toList();

    }

    private void endAnimations(List<Animation> removeList) {
        for (Animation animation : removeList) {
            animations.remove(animation);

        }
    }

    private boolean isEntityVisible(Entity entity) {
        return entity.getPoint().x() >= camera.getTileX() - 1 - camera.visionDistance
                && entity.getPoint().x() <= camera.getTileX() + camera.visionDistance + 1
                && entity.getPoint().y() >= camera.getTileY() - 1 - camera.visionDistance
                && entity.getPoint().y() <= camera.getTileY() + camera.visionDistance + 1;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        final int cellsLeft = (getWidth() - camera.visionSize * tileSize) / 2;
        final int cellsTop = Math.min((getHeight() - camera.visionSize * tileSize) / 2, 25);
        g.setColor(Color.WHITE);
        g.drawRect(cellsLeft-1, cellsTop-1, camera.visionSize * tileSize+1, camera.visionSize * tileSize+1);
        drawEntities(g, cellsLeft, cellsTop);
        drawAnimations(g, cellsLeft, cellsTop);
        drawInventory(g, cellsTop);
    }

    private void drawEntities(Graphics g, int left, int top) {
        g.clipRect(left, top, tileSize * camera.visionSize, tileSize * camera.visionSize);
        for (Entity entity : entities) {
            Sprite sprite = entity.getSprite();
            Point screenPos = worldToScreen(entity.getPoint());
            g.drawImage(sprite.image, left + screenPos.x(), top + screenPos.y(), null);
        }
        g.setClip(null);
    }

    private void drawInventory(Graphics g, int cellsTop) {
        final int left = (getWidth() - inventorySize * tileSize) / 2;
        final int top = (getHeight() + (cellsTop + tileSize * camera.visionSize) - tileSize) / 2;
        for (int i=0;i<inventorySize;i++) {
            g.drawRect(left + i * tileSize, top, tileSize, tileSize);
        }
        int i= 0;
        for (Key key : inventory.keySet()) {
            g.drawImage(key.getSprite().image, left + i * tileSize, top, null);
            if (inventory.get(key) > 1) {
                g.drawImage(Sprite.UI_TWO.image, left + i * tileSize, top, null);
            }

            i++;
        }
    }

    private void drawAnimations(Graphics g, int left, int top) {
        for (Animation anim : animations) {
            g.drawImage(
                    anim.getSprite().image, left + anim.getX()  - camera.getX() + (camera.visionDistance * tileSize),
                    top + anim.getY() - camera.getY() + (camera.visionDistance * tileSize), null);
        }
    }

    /**
     * Converts a tile coordinate into a point for rendering
     * @param point
     * @return
     */
    private Point worldToScreen(Point point) {
        return new Point((point.x() + camera.visionDistance) * tileSize - camera.getX(),
                (point.y() + camera.visionDistance) * tileSize - camera.getY());
    }


    public void addAnimation(Animation animation) {
        if (animation.getEntity() instanceof Player) {
            camera.addAnimation(animation.copy());
        }
        animations = new ArrayList<>(animations);
        animations.add(animation);
    }
}

