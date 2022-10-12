package nz.ac.vuw.ecs.swen225.gp22.domain;
/**
 * Directions that the Actor is able to move
 * @author Alicia Robinson - 300560663
 */
public enum Direction{
    None(0,0),
    Up(0,-1),
    Right(+1,0),
    Down(0,+1),
    Left(-1,0);
    /**
     *
     */
    public final Point arrow;

    /**
     * @param x x position
     * @param y y position
     */
    Direction(int x,int y){ arrow=new Point(x,y); }
}