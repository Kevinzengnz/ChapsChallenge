package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Objects;

/**
 * Represents points used to control where Entities are in the game.
 *
 * @param x x position
 * @param y y position
 * @author Alicia Robinson - 300560663
 */
public record Point(int x, int y) {
    /**
     * Adds given x and y to current x and y.
     *
     * @param x x position
     * @param y y position
     * @return new point after addition has been done
     */
    public Point add(int x, int y) {
        return new Point(x() + x, y() + y);
    }

    /**
     * Adds a new point to the current point.
     *
     * @param p point to be added
     * @return point after addition has been done
     */
    public Point add(Point p) {
        return add(p.x, p.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}