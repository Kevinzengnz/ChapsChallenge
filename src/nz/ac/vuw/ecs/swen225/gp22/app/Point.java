package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.Random;

/**
 * Point class for representing points in the game board
 * @author Kevin Zeng
 * ID: 300563468
 */
record Point(int x, int y){
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
}