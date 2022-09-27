package nz.ac.vuw.ecs.swen225.gp22.recorder;

/**
 * Class containing test utilities for recorder package. Will be deleted after.
 */
public class RecTesting {
    private static final boolean debug = false; //Set to true to show console debug messages.

    public static void log(String cls, String method, String message){
        if(debug){
            System.out.println("["+cls+"]"+" "+method+":: "+message);
        }
    }

    public static void createMockReplay(){
        Recorder r = new GameRecorder();
        r.startRecording("test_replay", "test_level");
        for(int i=0; i<60; i++){
            if(i%2==0) {
                r.ping(i % 3);
                r.ping(i % 3);
            }
        }
        r.endRecording();
        new Replay().loadReplay("test_replay");
    }

    public static void main(String[] a){
        createMockReplay();
    }
}