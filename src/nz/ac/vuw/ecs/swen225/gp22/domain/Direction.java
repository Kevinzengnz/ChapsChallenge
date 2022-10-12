package nz.ac.vuw.ecs.swen225.gp22.domain;
/**
 * Directions that the Actor is able to move
 * @author Alicia Robinson - 300560663
 */
public enum Direction{
    None(0,0){},
    Up(0,-1){
        @Override
        public Direction unUp(){return None;}
    },
    Right(+1,0){
        @Override
        public Direction unRight(){return None;}
    },
    Down(0,+1){
        @Override
        public Direction unDown(){return None;}
    },
    Left(-1,0){
        @Override
        public Direction unLeft(){return None;}
    };
    /**
     *
     */
    public final Point arrow;

    /**
     * @return
     */
    public Direction up(){return Up;}

    /**
     * @return
     */
    public Direction right(){return Right;}

    /**
     * @return
     */
    public Direction down(){return Down;}

    /**
     * @return
     */
    public Direction left(){return Left;}

    /**
     * @return
     */
    public Direction unUp(){return this;}

    /**
     * @return
     */
    public Direction unRight(){return this;}

    /**
     * @return
     */
    public Direction unDown(){return this;}

    /**
     * @return
     */
    public Direction unLeft(){return this;}


    /**
     * creates direction with given x and y position
     * @param x x position
     * @param y y position
     */
    Direction(int x,int y){ arrow=new Point(x,y); }
}