package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;


/**
 * Enum to store and load all the SoundEffects needed by the game.
 * @author Oliver Silk
 * ID: 300564261
 */
public enum SoundEffect {
    TREASURE("coin"),
    DOOR("unlock"),
    EXIT("portal"),
    KEY("key"),
    WALL("wall"),
    INFO("info");

    final Clip clip;

    /**
     * Loads the sound effect from the file system and create the SoundEffect object
     * @param name name of the SoundEffect to load
     */
    SoundEffect(String name) {
        String fileName = "Assets/Audio/" + name + ".wav";
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
            clip.open(inputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (name.equals("info")) gainControl.setValue(-10);
            else if (name.equals("key")) gainControl.setValue(gainControl.getMaximum());

            this.clip = clip;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
