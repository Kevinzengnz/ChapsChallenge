package nz.ac.vuw.ecs.swen225.gp22.app;

enum Direction{
    None(0d,0d){},
    Up(0d,-1d){
        @Override
        Direction unUp(){return None;}
    },
    Right(+1d,0d){
        @Override
        Direction unRight(){return None;}
    },
    Down(0d,+1d){
        Direction unDown(){return None;}
    },
    Left(-1d,0d){
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
    Point arrow(Double speed){ return arrow.times(speed,speed);}
    Direction(double x,double y){ arrow=new Point(x,y); }
}