package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.ChapsChallenge;
import nz.ac.vuw.ecs.swen225.gp22.app.PlayerController;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    private double speed=0.25;
    private Timer timer=null;
    private boolean isRunning=false;
    private int endPing=1;
    private int frames=0;
    private PlayerController pc;

    /**
     * Loads replay file data into this replay instance.
     * @param replayName File name of recording.
     */
    public void loadReplay(String replayName, ChapsChallenge game){
        this.cleanReplay();
        this.pc = game.getPhase().controller();
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
        this.actionList.remove(0);
        RecTesting.log("Replay", "loadReplay", "Replay loaded");
    }

    /**
     * Plays through replay until the end automatically.
     */
    public void autoPlay(){
        if(this.isRunning){return;}
        //Check if there is anything to replay.
        if(this.actionList==null||this.actionList.isEmpty()){
            RecTesting.log("Replay", "autoPlay", "No actions to replay");
            return;
        }
        this.isRunning=true;
        RecTesting.log("Replay", "autoPlay", "Auto play started");
        //Restart timer if it already exists and is paused.
        if(this.timer!=null){this.timer.restart();return;}
        //Otherwise create a new timer and start it.
        this.timer = new Timer(1000/30, x->{
            assert SwingUtilities.isEventDispatchThread();
            this.frames++;
            if(this.frames % (int)(4/this.speed) == 0){this.pings++;}
            step();
        });
        this.timer.start();
    }

    /**
     * Pauses automatic playback of the replay. Can be resumed later using autoPlay().
     */
    public void autoPause(){
        if(!this.isRunning){return;}
        this.isRunning=false;
        this.timer.stop();
    }

    /**
     * Sets playback speed multiplier for automatic playback of the replay.
     * @param speed Speed multiplier for automatic playback of replay. Must be greater than 0.
     */
    public void setReplaySpeed(double speed){this.speed=(speed>0)?speed:this.speed;}

    /**
     * Plays replay step by step. Call this method to move to the next step. Pauses automatic playback.
     */
    public void nextTick(){
        autoPause();
        this.pings++;
        step();
    }

    /**
     * Move to previous game tick in replay. Pauses autoplay.
     */
    private void prevTick(){
        autoPause();
    }

    /**
     * @return Whether automatic playback is running or not.
     */
    public boolean isRunning(){return this.isRunning;}

    /**
     * Move to the next tick of the game clock in the replay.
     */
    private void step(){
        if(this.pings==this.endPing){
            if(this.timer!=null){this.timer.stop();}
            RecTesting.log("Replay","autoPlay","Replay stopped at frame "+this.pings);
            cleanReplay();
        }
        this.actionList.stream().filter(a->a.frame()==this.pings).findFirst().ifPresentOrElse(
                (a)->{
                    RecTesting.log("Replay", "autoPlay", "Direction changed to: "+a.dir()+" at ping "+a.frame());
                    switch(a.dir()){
                        case 0 : this.pc.getActionsReleased().getOrDefault(KeyEvent.VK_W, ()->{}).run();break;
                        case 1 : this.pc.getActionsPressed().getOrDefault(KeyEvent.VK_W, ()->{}).run();break;
                        case 2 : this.pc.getActionsPressed().getOrDefault(KeyEvent.VK_D, ()->{}).run();break;
                        case 3 : this.pc.getActionsPressed().getOrDefault(KeyEvent.VK_S, ()->{}).run();break;
                        case 4 : this.pc.getActionsPressed().getOrDefault(KeyEvent.VK_A, ()->{}).run();break;
                    }
                },
                ()->{}
        );
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
        this.frames=0;
    }
}
