package nz.ac.vuw.ecs.swen225.gp22.domain;
import java.util.Objects;
import java.util.Random;
/**
 * @author Alicia Robinson - 300560663
 */
public record Point(int x, int y){
    //Note: x==width, y==height
    public Point add(int x,int y){
        return new Point(x()+x, y()+y);
    }
    public Point add(Point p){
        return add(p.x, p.y);
    }
    public Point times(int x, int y) {
        return new Point(x()*x, y()*y);
    }
    public Point times(int v) {
        return new Point(x()*v, y()*v);
    }

    public Point distance(Point other){
        return this.add(other.times(-1));
    }

    public double size(){//Pythagoras here!
        return Math.sqrt(x*x+y*y);
    }

    /**
     * Generates a random point inside the rectangle formed by
     * (minX,minY) and (maxX,maxY)
     * @param minX minimum x value
     * @param maxX maximum x value
     * @param minY minimum y value
     * @param maxY maximum y value
     * @return A new Point object with random coordinates inside the bounds
     */
    public static Point randomPoint(int minX, int maxX, int minY, int maxY) {
        Random random = new Random();
        return new Point(random.nextInt(maxX - minX) + minX,random.nextInt(maxY - minY) + minY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}