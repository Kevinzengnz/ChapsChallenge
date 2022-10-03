package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Replay class that will handle replaying a saved game.
 * @author Sankeerth Alookaran
 * ID: 300565439
 */
public class Replay {
    private List<Action> actionList;
    private int pings=0;
    private int tps=1;
    Timer timer=null;
    private boolean isRunning=false;
    private int endPing=0;

    /**
     * Loads a recording into the replay from specified file name.
     * @param replayName File name of recording.
     */
    public void loadReplay(String replayName){
        this.cleanReplay();
        Document replay = null;
        try {
            replay = XmlParser.parse(new File("Replays/" + replayName + ".xml"));
        } catch (DocumentException de) {
            RecTesting.log("Replay", "loadReplay", "Error loading replay file");
        }
        Element replayElem = replay.getRootElement().element("Replay");
        Element player = replayElem.element("Player");
        this.endPing = Integer.parseInt(replayElem.attribute("end").getValue());
        List<Element> actions = player.elements("action");
        for(Element action : actions){
            this.actionList.add(new Action(Integer.parseInt(action.attribute("dir").getValue()), Integer.parseInt(action.attribute("frame").getValue())));
        }
        RecTesting.log("Replay", "loadReplay", "Replay loaded");
    }

    /**
     * Plays replay automatically.
     */
    public void autoPlay(){
        if(this.isRunning){return;}
        this.isRunning=true;
        RecTesting.log("Replay", "autoPlay", "Auto play started");
        if(this.timer!=null){this.timer.restart();return;}
        this.timer = new Timer(136, x->{
            assert SwingUtilities.isEventDispatchThread();
            pings++;
            if(pings==endPing){
                timer.stop();
                RecTesting.log("Replay","autoPlay","Replay stopped at frame "+pings);
                cleanReplay();
            }
            actionList.stream().filter(a->a.frame()==pings).findFirst().ifPresentOrElse(
                    a->RecTesting.log("Replay", "autoPlay", "Direction changed to: "+a.dir()+" at ping "+a.frame()),
                    ()->{}
            );
        });
        this.timer.start();
    }

    /**
     * Pauses automatic replay.
     */
    public void autoPause(){
        if(!this.isRunning){return;}
        this.isRunning=false;
        this.timer.stop();
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

    /**
     * Resets all fields back to default
     */
    private void cleanReplay(){
        this.isRunning=false;
        this.actionList = new ArrayList<>();
        this.pings=0;
        this.endPing=1;
        if(this.timer!=null){this.timer.stop();this.timer=null;}
    }
}
