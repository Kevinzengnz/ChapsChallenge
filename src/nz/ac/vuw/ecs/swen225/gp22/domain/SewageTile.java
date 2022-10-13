package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

public class SewageTile extends Tile{
    /**
     * Creates new SewageTile with given sprite and point
     * @param point  position of Tile
     */
    protected SewageTile(Point point) {
        super("SEWAGE", point);
    }

    @Override
    public void doAction(Model model, Actor actor, Point point) {
        if(!this.getPoint().equals(point)){
            throw new IllegalArgumentException("Player point does not equal SewageTile Point");
        }
        model.onGameOver();
    }
}
