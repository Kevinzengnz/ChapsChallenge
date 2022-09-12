package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import nz.ac.vuw.ecs.swen225.gp22.app.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Recorder class that will handle recording a game.
 * @author Sankeerth Alookaran
 * ID: 300565439
 */
public class Recorder {
    private boolean isRecording;
    private String replayFile;

    /**
     * Start recording the current game into the specified save file path.
     * @param replayFile File to save recording into.
     */
    public void startRecording(String replayFile){
        if(!this.isRecording) {
            this.replayFile = replayFile;
            setRecording(true);
        }
    }

    /**
     * Ends the recording of the game and saves the replay file.
     */
    public void endRecording(){
        if(this.isRecording) {
            setRecording(false);
        }
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

    /**
     * Creates empty replay file at specified replay file path.
     */
    private void createReplayFile(){
        try{
            File newFile = new File("replays/"+this.replayFile+".xml");
            newFile.createNewFile();
            FileWriter newWriter = new FileWriter("replays/"+this.replayFile+".xml");
            newWriter.write("<test></test>");
            newWriter.close();
        }catch (IOException e){
            System.err.println("Error creating replay file "+this.replayFile);
            e.printStackTrace();
        }
    }
}
