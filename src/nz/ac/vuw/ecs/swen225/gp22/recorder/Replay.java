package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import javax.print.Doc;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Replay class that will handle replaying a saved game.
 * @author Sankeerth Alookaran
 * ID: 300565439
 */
public class Replay {
    private int tps;
    private List<Action> actionList;

    /**
     * Loads a recording into the replay from specified file name.
     * @param replayName File name of recording.
     */
    public void loadReplay(String replayName){
        this.actionList = new ArrayList<>();
        Document replay = null;
        try {
            replay = XmlParser.parse(new File("Replays/" + replayName + ".xml"));
        } catch (DocumentException de) {
            RecTesting.log("Replay", "loadReplay", "Error loading replay file");
        }
        Element player = replay.getRootElement().element("test_level").element("Player");
        List<Element> actions = player.elements("action");
        for(Element action : actions){
            this.actionList.add(new Action() {
                @Override
                public int frame() {
                    return Integer.parseInt(action.attribute("frame").getValue());
                }
                @Override
                public int dir() {
                    return Integer.parseInt(action.attribute("dir").getValue());
                }
            });
        }
        for(Action a : this.actionList){
            RecTesting.log("Replay", "loadReplay", a.dir()+" at frame : "+a.frame());
        }
        //RecTesting.log("Replay", "loadReplay", replay.asXML());
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
