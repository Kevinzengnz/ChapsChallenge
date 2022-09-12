package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.Random;
record Point(double x, double y){
    //Note: x==with, y==height
    public Point add(double x,double y){
        return new Point(x()+x, y()+y);
    }
    public Point add(Point p){
        return add(p.x, p.y);
    }
    public Point times(double x, double y) {
        return new Point(x()*x, y()*y);
    }
    public Point times(double v) {
        return new Point(x()*v, y()*v);
    }

    public Point distance(Point other){
        return this.add(other.times(-1));
    }

    public double size(){//Pythagoras here!
        return Math.sqrt(x*x+y*y);
    }

    public static Point randomPoint(int minX, int maxX, int minY, int maxY) {
        Random random = new Random();
        return new Point(random.nextInt(maxX - minX) + minX,random.nextInt(maxY - minY) + minY);
    }
}
