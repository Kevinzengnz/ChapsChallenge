package nz.ac.vuw.ecs.swen225.gp22.app;

/**
 *
 * @author Kevin Zeng
 * ID: 300563468
 */
enum Direction{
    None(0,0){},
    Up(0,-1){
        @Override
        Direction unUp(){return None;}
    },
    Right(+1,0){
        @Override
        Direction unRight(){return None;}
    },
    Down(0,+1){
        Direction unDown(){return None;}
    },
    Left(-1,0){
        @Override
        Direction unLeft(){return None;}
    };
    public final Point arrow;
    Direction up(){return Up;}
    Direction right(){return Right;}
    Direction down(){return Down;}
    Direction left(){return Left;}
    Direction unUp(){return this;}
    Direction unRight(){return this;}
    Direction unDown(){return this;}
    Direction unLeft(){return this;}
    Point arrow(Integer speed){ return arrow.times(speed,speed);}
    Direction(int x,int y){ arrow=new Point(x,y); }
}