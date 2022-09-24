package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Recorder class that will handle recording a game.
 * @author Sankeerth Alookaran
 * ID: 300565439
 */
public class Recorder {
    private boolean isRecording;
    private String replayFile;
    private List<Integer> actionHistory;
    //Debug
    private boolean debug = false;

    /**
     * Start recording the current game into the specified save file path.
     * @param replayFile File to save recording into.
     */
    public void startRecording(String replayFile){
        if(!this.isRecording) {
            this.actionHistory = new ArrayList<>();
            this.replayFile = replayFile;
            setRecording(true);
        }
    }

    /**
     * Ends the recording of the game and saves the replay file.
     */
    public void endRecording(){
        if(this.isRecording) {
            saveRecording();
            setRecording(false);
        }
    }

    /**
     * Call this function every time an action takes place in the game. Will save the action to history for recording.
     * @param action Key code of action.
     */
    public void onAction(int action){
        if(this.isRecording){
            this.actionHistory.add(action);
            //Remove below for final
            RecTesting.log("Recorder", "onAction", "Added action "+action);
        }
    }

    /**
     * Sets recording state true or false.
     * @param isRecording true if game should be recorded.
     */
    private void setRecording(boolean isRecording){
        this.isRecording=isRecording;
    }

    /**
     * Saves recording into the replay file.
     */
    private void saveRecording(){
        try {
            FileWriter out = new FileWriter(new File("Replays/", this.replayFile+".txt"));
            for(int i : actionHistory){
                out.write(i+"\n");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
