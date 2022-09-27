package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//XML
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Recorder class that will handle recording a game.
 * @author Sankeerth Alookaran
 * ID: 300565439
 */
public class GameRecorder implements Recorder{
    private boolean isRecording;
    private String replayFile;
    private List<Integer> actionHistory;
    private List<Integer> frameHistory;
    private String level;
    private int frame = 0;
    private int prevDir = -1;

    /**
     * Start recording the current game into the specified save file path.
     * @param replayFile File to save recording into.
     * @param level Name of current level to be recorded.
     */
    @Override
    public void startRecording(String replayFile, String level){
        if(!this.isRecording) {
            this.actionHistory = new ArrayList<>();
            this.frameHistory = new ArrayList<>();
            this.level = String.join("", level.split(" "));
            this.replayFile = (replayFile.endsWith(".xml")) ? Arrays.stream(replayFile.split(".xml")).findFirst().orElse("default") : replayFile;
            setRecording(true);
            RecTesting.log("GameRecorder", "startRecording", "Starting recording for "+this.level+" at Replays/"+this.replayFile);
        }
    }

    /**
     * Ends the recording of the game and saves the replay file.
     */
    @Override
    public void endRecording(){
        if(this.isRecording) {
            saveRecording();
            setRecording(false);
        }
    }

    /**
     * Call this function every time an action takes place in the game. Will save the action to history for recording.
     * @param dir Ordinal of direction.
     */
    @Override
    public void ping(int dir){
        this.frame++;
        if(this.isRecording && this.prevDir !=dir){
            this.actionHistory.add(dir);
            this.frameHistory.add(this.frame);
            this.prevDir=dir;
            //Remove below for final
            RecTesting.log("GameRecorder", "onAction", "Added action "+dir);
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
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        Element level = root.addElement(this.level);
        Element player = level.addElement("Player");

        for(int i=0 ; i<actionHistory.size(); i++){
            player.addElement("action")
                    .addAttribute("dir", String.valueOf(actionHistory.get(i)))
                    .addAttribute("frame", String.valueOf(frameHistory.get(i)));
        }

        try {
            XmlParser.write(doc, this.replayFile+".xml", "Replays/");
            RecTesting.log("GameRecorder", "saveRecording", "Saved replay to Replays/"+this.replayFile);
        } catch (IOException e) {
            RecTesting.log("GameRecorder", "saveRecording", "IOException : "+e.getMessage());
        }
    }
}
