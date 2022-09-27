package nz.ac.vuw.ecs.swen225.gp22.domain;
/**
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
        public Direction unDown(){return None;}
    },
    Left(-1,0){
        @Override
        public Direction unLeft(){return None;}
    };
    public final Point arrow;
    public Direction up(){return Up;}
    public Direction right(){return Right;}
    public Direction down(){return Down;}
    public Direction left(){return Left;}
    public Direction unUp(){return this;}
    public Direction unRight(){return this;}
    public Direction unDown(){return this;}
    public Direction unLeft(){return this;}
    public Point arrow(Integer speed){ return arrow.times(speed,speed);}
    Direction(int x,int y){ arrow=new Point(x,y); }
}