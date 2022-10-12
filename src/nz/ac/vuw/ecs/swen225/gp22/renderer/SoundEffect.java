package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public enum SoundEffect {
    TREASURE("coin"),
    DOOR("unlock"),
    EXIT("portal"),
    KEY("key"),
    WALL("wall"),
    INFO("info");

    final Clip clip;
    SoundEffect(String name) {
        String fileName = "Assets/Audio/" + name + ".wav";
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
            clip.open(inputStream);
            this.clip = clip;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
