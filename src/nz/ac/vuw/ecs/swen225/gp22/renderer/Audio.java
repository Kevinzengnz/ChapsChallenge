package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.sound.sampled.Clip;

public class Audio {
    public static void playSoundEffect(String name) {
        Clip clip = getSoundEffect(name).clip;
        clip.setFramePosition(0);
        clip.start();
    }
    public static SoundEffect getSoundEffect(String name) {
        return SoundEffect.valueOf(name.toUpperCase());
    }

    public static SoundPlayer getSoundPlayer(String soundName) {
        return () -> playSoundEffect(soundName);
    }

}

interface SoundPlayer {
    void playSound();
}