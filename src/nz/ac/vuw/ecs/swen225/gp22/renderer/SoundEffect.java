package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public enum SoundEffect {
    COIN("coin"),
    UNLOCK("unlock"),
    PORTAL("portal"),
    KEY("key");

    final Clip clip;
    final String name;
    SoundEffect(String name) {
        this.name = name;
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
