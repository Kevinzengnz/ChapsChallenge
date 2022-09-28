package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class for the renderer. Handles drawing of game board and inventory.
 * @author Oliver Silk
 * ID: 300564261
 */
public class Renderer extends JPanel {

    private final Camera camera = new Camera(5, 5);;
    // Drawing constants
    private final int tileSize = 64;

    Map<Key, Integer> inventory = new HashMap<>();

    // Entities currently visible
    private List<Entity> entities = new ArrayList<>();

    private Map<Actor, Point> actors = new HashMap<>();

    private List<Animation> animations = new ArrayList<>();


    /**
     * Updates the renderer with drawing information.
     * @param cameraPosition the position of the camera (center)
     * @param allEntities list of all entities in the game requiring rendering
     * @param keys list of keys in the players inventory
     */
    public void ping(Point cameraPosition, List<Entity> allEntities, List<Key> keys) {
        camera.update(cameraPosition);
        // Update inventory
        inventory.clear();
        for (Key key : keys) {
            if (inventory.containsKey(key)) inventory.put(key, inventory.get(key)+1);
            else inventory.put(key, 1);
        }
        // Update animations
        animations.forEach(Animation::ping);
        animations.removeAll(animations.stream().filter(Animation::isFinished).toList());

        for(Actor actor : actors.keySet()) {
            for (Entity entity : allEntities) {
                if (actor.equals(entity) && !actors.get(actor).equals(entity.getPoint())) {
                    addAnimation(new WalkAnimation(actors.get(actor), entity.getPoint(), 4, entity));
                }
            }
        }
        actors = allEntities.stream()
                .filter(e -> e instanceof Actor)
                .map(e -> (Actor) e)
                .collect(Collectors.toMap(a -> a, Actor::getPoint));

        // Update entities
        entities = new ArrayList<>();
        entities = allEntities.stream()
                .filter(this::isEntityVisible)
                .filter(e -> animations.stream().noneMatch(a -> a.getEntity().equals(e)))
                .sorted(Comparator.comparingInt(Entity::getDepth))
                .toList();
    }


    /**
     * Checks whether the provided entity is visible to the camera (or within 1 tile of the camera).
     * @param entity the entity to check visibility of
     * @return true if entity is visible, false if not
     */
    private boolean isEntityVisible(Entity entity) {
        return entity.getPoint().x() >= camera.getTileX() - 1 - camera.visionDistance
                && entity.getPoint().x() <= camera.getTileX() + camera.visionDistance + 1
                && entity.getPoint().y() >= camera.getTileY() - 1 - camera.visionDistance
                && entity.getPoint().y() <= camera.getTileY() + camera.visionDistance + 1;
    }

    /**
     * Paints the game onto the screen.
     * @param g the Graphics object to draw on
     */
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

    /**
     * Draws all  the entities onto the game board.
     * @param g the graphics object to draw on
     * @param left the left side of the game board
     * @param top the top of the game board
     */
    private void drawEntities(Graphics g, int left, int top) {
        g.clipRect(left, top, tileSize * camera.visionSize, tileSize * camera.visionSize);
        for (Entity entity : entities) {
            Sprite sprite = entity.getSprite();
            Point screenPos = worldToScreen(entity.getPoint());
            g.drawImage(sprite.image, left + screenPos.x(), top + screenPos.y(), null);
        }
        g.setClip(null);
    }

    /**
     * Draws the inventory panel and the keys the player has collected.
     * @param g the graphics object to draw on
     * @param cellsTop the top of the game board cells
     */
    private void drawInventory(Graphics g, int cellsTop) {
        final int inventorySize = 4;
        final int left = (getWidth() - inventorySize * tileSize) / 2;
        final int top = (getHeight() + (cellsTop + tileSize * camera.visionSize) - tileSize) / 2;
        for (int i = 0; i< inventorySize; i++) {
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

    /**
     * Draws all the animations.
     * @param g the graphics object to draw on
     * @param left the left side of the game board
     * @param top the top of the game board
     */
    private void drawAnimations(Graphics g, int left, int top) {
        for (Animation anim : animations) {
            g.drawImage(
                    anim.getSprite().image, left + anim.getX()  - camera.getX() + (camera.visionDistance * tileSize),
                    top + anim.getY() - camera.getY() + (camera.visionDistance * tileSize), null);
        }
    }

    /**
     * Converts a tile coordinate into a point for rendering
     * @param point the tile coordinate to convert
     * @return the point in screen coordinates for rendering
     */
    private Point worldToScreen(Point point) {
        return new Point((point.x() + camera.visionDistance) * tileSize - camera.getX(),
                (point.y() + camera.visionDistance) * tileSize - camera.getY());
    }


    /**
     * Adds an animation to the animations list
     * @param animation the animation to add
     */
    public void addAnimation(Animation animation) {
        animations.removeAll(animations.stream().filter(a -> a.getEntity().equals(animation.getEntity())).toList());
        if (animation.getEntity() instanceof Player) {
            camera.removeAnimation();
            camera.addAnimation(animation.copy());
        }
        animations = new ArrayList<>(animations);
        animations.add(animation);
    }
}

