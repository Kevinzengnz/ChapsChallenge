package nz.ac.vuw.ecs.swen225.gp22.domain;


import java.util.Arrays;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents Robot for level 2 of the game.
 *
 * @author Alicia Robinson - 300560663
 */
public class Robot extends Actor {
    /**
     * Directions robot can face.
     */
    List<Direction> directions = Arrays.asList(Direction.Right,
            Direction.Down, Direction.Left, Direction.Up);
    /**
     * Robots possible sprites.
     */
    List<String> sprites = Arrays.asList("ROBOT_RIGHT", "ROBOT_DOWN", "ROBOT_LEFT", "ROBOT_UP");
    /**
     * Index for sprites and directions lists.
     */
    int movementIndex = 0;
    /**
     * Robots sound effect for when player is killed.
     */
    public Runnable soundEffect;

    /**
     * Creates a Robot from the given point.
     *
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
                .filter(entity -> (entity instanceof WallTile
                        || entity instanceof Door || entity instanceof SewageTile
                        || entity instanceof Player)
                        && entity.getPoint().equals(newPoint))
                .forEach(entity -> entity.checkRobotMove(m, this, newPoint));
        if (!moveValid) {
            movementIndex += 1;
            if (movementIndex == 4) {
                movementIndex = 0;
            }
            setDirection(directions.get(movementIndex));
            setSpriteName(sprites.get(movementIndex));
        } else {
            this.point = newPoint;
        }
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        if (!this.getPoint().equals(point)) {
            throw new IllegalArgumentException("Player point does not equal Robot Point");
        }
        soundEffect.run();
        model.onGameOver();
    }

    @Override
    public void setSoundEffect(Runnable soundEffect) {
        if (soundEffect == null) {
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }
}
