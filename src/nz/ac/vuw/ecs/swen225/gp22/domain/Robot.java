package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

import java.util.Arrays;
import java.util.List;

public class Robot extends Actor{
    List<Direction> directions = Arrays.asList(Direction.Right, Direction.Down, Direction.Left, Direction.Up);
    List<String> sprites = Arrays.asList("ROBOT_RIGHT", "ROBOT_DOWN", "ROBOT_LEFT", "ROBOT_UP");
    int movementIndex = 0;

    /**
     * Creates a Robot from the given point
     * @param point Starting position of Robot
     */
    protected Robot(Point point) {
        super("ROBOT_RIGHT", point);
        setDirection(directions.get(movementIndex));
    }

    @Override
    public void ping(Model m) {
        moveValid = true;
        Point newPoint = point.add(direction.arrow);
        m.entities().stream()
                .filter(entity -> (entity instanceof WallTile)
                        && entity.getPoint().equals(newPoint))
                .forEach(entity -> entity.doAction(m, this, newPoint));
        if(!moveValid){
            movementIndex += 1;
            if(movementIndex == 4){
                movementIndex = 0;
            }
            setDirection(directions.get(movementIndex));
            setSpriteName(sprites.get(movementIndex));
        } else{
            this.point = newPoint;
        }
    }

    @Override
    public void doAction(Model model, Actor actor, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal SewageTile Point");
        }
        model.onGameOver();
    }



}
