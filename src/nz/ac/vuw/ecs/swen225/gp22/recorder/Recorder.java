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
     * Start recording the current game into the specified save file path.
     * @param saveFile File to save recording into.
     */
    public void startRecording(String saveFile){
        setRecording(true);
    }

    /**
     * Ends the recording of the game and saves the replay file.
     */
    public void endRecording(){
        setRecording(false);
    }

    /**
     * Call this function every time an action takes place in the game. Will save the action to history for recording.
     */
    public void onAction(){

    }

    /**
     * Sets recording state true or false.
     * @param isRecording true if game should be recorded.
     */
    private void setRecording(boolean isRecording){
        this.isRecording=isRecording;
    }
}
