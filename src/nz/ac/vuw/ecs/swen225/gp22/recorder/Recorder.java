package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import nz.ac.vuw.ecs.swen225.gp22.app.*;

/**
 * Recorder class that will handle recording a game.
 * @author Sankeerth Alookaran
 * ID: 300565439
 */
public class Recorder {
    private boolean isRecording;

    /**
     * Call this function every time an action takes place in the game. Will save the action to history for recording.
     */
    public void onAction(){

    }

    /**
     * Sets recording state true or false.
     * @param isRecording true if game should be recorded.
     */
    public void setRecording(boolean isRecording){
        this.isRecording=isRecording;
    }
}
