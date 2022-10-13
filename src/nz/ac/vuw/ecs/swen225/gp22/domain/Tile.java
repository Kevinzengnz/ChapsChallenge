package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents tiles in the game.
 * @author Alicia Robinson - 300560663
 */
public class Tile implements Entity{
    /**
     * String of the sprite name
     */
    private final String spriteName;
    /**
     * Point that Tile is at
     */
    private final Point point;

    /**
     * Creates new Tile with given sprite and point
     * @param spriteName name of Tiles sprite
     * @param point position of Tile
     */
    public Tile(String spriteName, Point point) {
        this.spriteName = spriteName;
        this.point = point;
    }

    @Override
    public String getSpriteName() {
        return this.spriteName;
    }

    @Override
    public Point getPoint() { return this.point; }

    @Override
    public int getDepth() {
        return 0;
    }
}

