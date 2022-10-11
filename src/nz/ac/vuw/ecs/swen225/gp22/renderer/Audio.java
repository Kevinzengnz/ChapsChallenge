package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.sound.sampled.Clip;

public class Audio {
    public static void playSoundEffect(String name) {
        if (name == null) return;
        Clip clip = getSoundEffect(name).clip;
        if (clip.isRunning()) return;
        clip.setFramePosition(0);
        clip.start();
    }
    public static SoundEffect getSoundEffect(String name) {
        if (name.startsWith("DOOR")) name = "DOOR";
        if (name.startsWith("KEY")) name = "KEY";
        return SoundEffect.valueOf(name.toUpperCase());
    }

    public static Runnable getSoundPlayer(String soundName) {
        return () -> playSoundEffect(soundName);
    }

}
