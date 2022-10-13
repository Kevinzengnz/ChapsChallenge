package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.Actor;

public interface ExtraActor {
    Actor createEntity();

    public void ping();

    public void doAction();


}
