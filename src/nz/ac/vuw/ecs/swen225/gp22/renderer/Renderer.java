package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import nz.ac.vuw.ecs.swen225.gp22.domain.Actor;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.InfoTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Point;


/**
 * Main class for the renderer. Handles drawing of game board and inventory.
 *
 * @author Oliver Silk 300564261
 */
public class Renderer extends JPanel {

  /**
   * The camera. Used to represent the focus area of the game.
   */
  private final Camera camera = new Camera(new Point(5, 5));
  /**
   * The size in pixels of the tiles.
   */
  private static final int tileSize = 64;

  /**
   * Stores the Keys the character has picked up for drawing. Updated every ping.
   */
  Map<Key, Integer> inventory = new HashMap<>();

  /**
   * Stores the entities for drawing. Updated every ping.
   */
  private List<Entity> entities = new ArrayList<>();

  /**
   * Stores the actors to determine which have moved and need animations. Updated every ping.
   */
  private Map<Actor, Point> actors = new HashMap<>();

  /**
   * Stores all the currently running animations.
   */
  private List<Animation> animations = new ArrayList<>();

  /**
   * Message to display on the screen. Used to display help messages.
   */
  private String message;
  /**
   * Timer to display above message for. Counts down till 0 then hides message.
   */
  private int messageTimer = 0;

  /**
   * The font used for displaying popup messages.
   */
  private final Font font = new Font("Courier",Font.PLAIN,16);

  /**
   * Updates the renderer with drawing information.
   *
   * @param cameraPosition the position of the camera (center)
   * @param allEntities    list of all entities in the game requiring rendering
   * @param keys           list of keys in the players inventory
   */
  public void ping(Point cameraPosition, List<Entity> allEntities, List<Key> keys) {
    if (messageTimer != 0) {
      messageTimer--;
    }
    camera.update(cameraPosition);
    // Update inventory
    inventory.clear();
    for (Key key : keys) {
      if (inventory.containsKey(key)) {
        inventory.put(key, inventory.get(key) + 1);
      } else {
        inventory.put(key, 1);
      }
    }
    // Update animations
    animations.forEach(Animation::ping);
    animations.removeAll(animations.stream().filter(Animation::isFinished).toList());

    for (Map.Entry<Actor, Point> actorEntry : actors.entrySet()) {
      Actor actor = actorEntry.getKey();
      Point point = actorEntry.getValue();
      for (Entity entity : allEntities) {
        if (actor.equals(entity) && !point.equals(entity.getPoint())) {
          if (actor instanceof Player) {
            addAnimation(new WalkAnimation(point, entity.getPoint(), 4, entity));
          } else {
            addAnimation(new MoveAnimation(point, entity.getPoint(), 4, entity));
          }
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
   *
   * @param entity the entity to check visibility of
   * @return true if entity is visible, false if not
   */
  private boolean isEntityVisible(Entity entity) {
    return entity.getPoint().x() >= camera.getTileX() - 1 - camera.getVisionDistance()
            && entity.getPoint().x() <= camera.getTileX() + camera.getVisionDistance() + 1
            && entity.getPoint().y() >= camera.getTileY() - 1 - camera.getVisionDistance()
            && entity.getPoint().y() <= camera.getTileY() + camera.getVisionDistance() + 1;
  }

  /**
   * Paints the game onto the screen.
   *
   * @param g the Graphics object to draw on
   */
  @Override
  public void paintComponent(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());
    final int cellsLeft = (getWidth() - camera.getVisionSize() * tileSize) / 2;
    final int cellsTop = Math.min((getHeight() - camera.getVisionSize() * tileSize) / 2, 25);
    g.setColor(Color.WHITE);
    g.drawRect(cellsLeft - 1, cellsTop - 1, camera.getVisionSize() * tileSize + 1,
            camera.getVisionSize() * tileSize + 1);
    drawEntities(g, cellsLeft, cellsTop);
    drawAnimations(g, cellsLeft, cellsTop);
    drawInventory(g, cellsTop);

    List<InfoTile> infoTiles = entities.stream()
            .filter(e -> e.getPoint().equals(camera.getTilePoint()) && e instanceof InfoTile)
            .map(e -> (InfoTile) e)
            .toList();
    if (infoTiles.size() != 0) {
      drawPopup(g, cellsLeft, cellsTop, infoTiles.get(0).getText());
    }
    if (messageTimer != 0) {
      drawPopup(g, cellsLeft, cellsTop, message);
    }

  }

  /**
   * Draws all  the entities onto the game board.
   *
   * @param g    the graphics object to draw on
   * @param left the left side of the game board
   * @param top  the top of the game board
   */
  private void drawEntities(Graphics g, int left, int top) {
    g.clipRect(left, top, tileSize * camera.getVisionSize(), tileSize * camera.getVisionSize());
    for (Entity entity : entities) {
      Sprite sprite = Sprite.valueOf(entity.getSprite());
      Point screenPos = worldToScreen(entity.getPoint());
      g.drawImage(sprite.image, left + screenPos.x(), top + screenPos.y(), null);
    }
    g.setClip(null);
  }

  /**
   * Draws the inventory panel and the keys the player has collected.
   *
   * @param g        the graphics object to draw on
   * @param cellsTop the top of the game board cells
   */
  private void drawInventory(Graphics g, int cellsTop) {
    final int inventorySize = 4;
    final int left = (getWidth() - inventorySize * tileSize) / 2;
    final int top = (getHeight() + (cellsTop + tileSize * camera.getVisionSize()) - tileSize) / 2;
    for (int i = 0; i < inventorySize; i++) {
      g.drawRect(left + i * tileSize, top, tileSize, tileSize);
    }
    int i = 0;
    for (Map.Entry<Key, Integer> keyEntry : inventory.entrySet()) {
      Image img = Sprite.valueOf(keyEntry.getKey().getSprite()).image;
      g.drawImage(img, left + i * tileSize, top, null);
      if (keyEntry.getValue() > 1) {
        g.drawImage(Sprite.UI_TWO.image, left + i * tileSize, top, null);
      }

      i++;
    }
  }


  /**
   * Draws text popups to display InfoTiles.
   *
   * @param g       the graphics object to draw on
   * @param left    the left side of the game board
   * @param top     the top of the game board
   * @param message the message to draw
   */
  private void drawPopup(Graphics g, int left, int top, String message) {
    if (message == null) {
      return;
    }
    List<String> list = new ArrayList<>();
    StringBuilder line = new StringBuilder();
    for (String s : message.split(" ")) {
      if (line.length() + s.length() > 40) {
        list.add(line.toString());
        line = new StringBuilder();
      }
      else {
        line.append(s).append(" ");
      }
    }
    list.add(line.toString());

    g.setFont(font);
    Image bg = Sprite.TEXT_POPUP.image;
    int drawLeft = left + (camera.getVisionSize() * tileSize - bg.getWidth(null)) / 2;
    int drawTop = top + 20;
    g.drawImage(bg, drawLeft, drawTop, null);;
    for (int i = 0; i < list.size(); i++) {
      g.drawString(list.get(i), drawLeft + 10, drawTop + 20 * (i + 1));
    }

  }

  /**
   * Displays a popup with the message for the specified number of pings.
   *
   * @param message the message to add
   * @param length  the number of pings to display it for
   */
  public void addPopup(String message, int length) {
    this.message = message;
    this.messageTimer = length;
  }

  /**
   * Draws all the animations.
   *
   * @param g    the graphics object to draw on
   * @param left the left side of the game board
   * @param top  the top of the game board
   */
  private void drawAnimations(Graphics g, int left, int top) {
    for (Animation anim : animations) {
      g.drawImage(
              anim.getSprite().image,
              left + anim.getX() - camera.getX() + (camera.getVisionDistance() * tileSize),
              top + anim.getY() - camera.getY() + (camera.getVisionDistance() * tileSize),
              null);
    }
  }

  /**
   * Converts a tile coordinate into a point for rendering.
   *
   * @param point the tile coordinate to convert
   * @return the point in screen coordinates for rendering
   */
  private Point worldToScreen(Point point) {
    return new Point((point.x() + camera.getVisionDistance()) * tileSize - camera.getX(),
            (point.y() + camera.getVisionDistance()) * tileSize - camera.getY());
  }


  /**
   * Adds an animation to the animations list.
   *
   * @param animation the animation to add
   */
  public void addAnimation(Animation animation) {
    animations.removeAll(animations.stream()
            .filter(a -> a.getEntity().equals(animation.getEntity())).toList());
    if (animation.getEntity() instanceof Player) {
      camera.removeAnimation();
      camera.addAnimation(animation.copy());
    }
    animations = new ArrayList<>(animations);
    animations.add(animation);
  }
}

