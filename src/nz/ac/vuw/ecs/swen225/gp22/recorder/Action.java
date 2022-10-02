package nz.ac.vuw.ecs.swen225.gp22.recorder;

class Action {
    private int dir;
    private int frame;

    /**
     * Constructor for action
     * @param dir Direction player changed to
     * @param frame Frame number of direction changed
     */
    public Action(int dir, int frame){
        this.dir=dir;
        this.frame=frame;
    }

    //GETTERS
    public int dir(){return this.dir;}
    public int frame(){return this.frame;}
}
