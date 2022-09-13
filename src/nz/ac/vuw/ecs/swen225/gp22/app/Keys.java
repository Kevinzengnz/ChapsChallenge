package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

/**
 *
 * @author Kevin Zeng
 * ID: 300563468
 */
class Keys implements KeyListener {
    private Map<Integer,Runnable> actionsPressed = new HashMap<>();
    private Map<Integer,Runnable> actionsReleased = new HashMap<>();
    public void setAction(int keyCode,Runnable onPressed,Runnable onReleased){
        actionsPressed.put(keyCode,onPressed);
        actionsReleased.put(keyCode,onReleased);

    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e){
        assert SwingUtilities.isEventDispatchThread();
        actionsPressed.getOrDefault(e.getKeyCode(),()->{}).run();
        if(e.isControlDown()) {
            ctrlActionsPressed.getOrDefault(e.getKeyCode(), () -> {}).run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        assert SwingUtilities.isEventDispatchThread();
        actionsReleased.getOrDefault(e.getKeyCode(),()->{}).run();
        if(e.isControlDown()) {
            ctrlActionsReleased.getOrDefault(e.getKeyCode(), () -> {}).run();
        }
    }


    //functionality for control
    private Map<Integer,Runnable> ctrlActionsPressed = new HashMap<>();
    private Map<Integer,Runnable> ctrlActionsReleased = new HashMap<>();
    public void setCtrlAction(int keyCode,Runnable onPressed,Runnable onReleased){
        ctrlActionsPressed.put(keyCode,onPressed);
        ctrlActionsReleased.put(keyCode,onReleased);
    }
}