package nz.ac.vuw.ecs.swen225.gp22.recorder;

class Action {
    /**
     * Direction of movement change.
     */
    private final int dir;
    /**
     * Game logic clock frame the movement change occurred in.
     */
    private final int frame;

    /**
     * Constructor for action.
     * @param direction Direction player changed to
     * @param crntFrame Frame number of direction changed
     */
    Action(final int direction, final int crntFrame) {
        this.dir = direction;
        this.frame = crntFrame;
    }

    //GETTERS
    public int dir() {
        return this.dir;
    }
    public int frame() {
        return this.frame;
    }
}
