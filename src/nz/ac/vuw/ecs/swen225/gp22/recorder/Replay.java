package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import javax.print.Doc;
import java.io.File;

/**
 * Replay class that will handle replaying a saved game.
 * @author Sankeerth Alookaran
 * ID: 300565439
 */
public class Replay {
    private int tps;

    /**
     * Loads a recording into the replay from specified file name.
     * @param replayName File name of recording.
     */
    public void loadReplay(String replayName){
        Document replay = null;
        try {
            replay = new XmlParser().parse(new File("Replays/" + replayName + ".xml"));
        } catch (DocumentException de) {
            RecTesting.log("Replay", "loadReplay", "Error loading replay file");
        }

        RecTesting.log("Replay", "loadReplay", replay.asXML());
    }

    /**
     * Plays replay automatically.
     */
    public void autoPlay(){

    }

    /**
     * Pauses automatic replay.
     */
    public void autoPause(){

    }

    /**
     * Sets speed of automatic replay in game ticks per second.
     * @param tps Number of game ticks per second. Must be above 0.
     */
    public void setReplaySpeed(int tps){this.tps=(tps>0)?tps:this.tps;}

    /**
     * Move to next game tick in replay. Pauses autoplay.
     */
    public void nextTick(){
        autoPause();
    }

    /**
     * Move to previous game tick in replay. Pauses autoplay.
     */
    public void prevTick(){
        autoPause();
    }
}
