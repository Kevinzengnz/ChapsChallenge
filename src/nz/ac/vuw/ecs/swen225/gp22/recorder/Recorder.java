package nz.ac.vuw.ecs.swen225.gp22.recorder;

public interface Recorder {
    /**
     * Start recording the current game into the specified save file path.
     * @param replayFile File to save recording into.
     * @param level Name of current level to be recorded.
     */
    void startRecording(String replayFile, String level);
    /**
     * Ends the recording of the game.
     */
    void endRecording();
    /**
     * Call this function every time an action takes place in the game.
     * @param dir Ordinal of Direction.
     */
    void ping(int dir);
}
