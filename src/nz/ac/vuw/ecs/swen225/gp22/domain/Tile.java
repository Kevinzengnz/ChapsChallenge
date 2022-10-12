package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents tiles in the game.
 * @author Alicia Robinson - 300560663
 */
public class Tile implements Entity{
    /**
     * String of the sprite name
     */
    private final String sprite;
    /**
     * Point that Tile is at
     */
    private final Point point;

    /**
     * Creates new Tile with given sprite and point
     * @param sprite name of Tiles sprite
     * @param point position of Tile
     */
    public Tile(String sprite, Point point) {
        if(point == null || sprite == null){
            throw new IllegalArgumentException("Tile Sprite or Point is null");
        }
        this.sprite = sprite;
        this.point = point;
    }

    @Override
    public String getSprite() {
        return this.sprite;
    }

    @Override
    public Point getPoint() { return this.point; }

    @Override
    public int getDepth() {
        return 0;
    }
}

