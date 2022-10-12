package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.sound.sampled.Clip;

/**
 * Abstract class to handle the playing of Sound Effects.
 *
 * @author Oliver Silk 300564261
 */
public abstract class Audio {
  /**
   * Plays the given SoundEffect if it is not already playing.
   *
   * @param name the name of the sound effect to play
   */
  public static void playSoundEffect(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null");
    }
    Clip clip = getSoundEffect(name).clip;
    if (clip.isRunning()) {
      return;
    }
    clip.setFramePosition(0);
    clip.start();
  }

  /**
   * Gets a SoundEffect object from its name.
   *
   * @param name name of SoundEffect
   * @return SoundEffect object
   */
  public static SoundEffect getSoundEffect(String name) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name must not be blank");
    }
    if (name.startsWith("DOOR")) {
      name = "DOOR";
    }
    if (name.startsWith("KEY")) {
      name = "KEY";
    }
    return SoundEffect.valueOf(name.toUpperCase());
  }

  /**
   * Gets a Runnable to play the passed in sound name. Allows Domain to play sound effects
   * when conditions are met without having to depend on Renderer.
   *
   * @param name name of sound effect to play in Runnable
   * @return Runnable to play the sound effect
   */
  public static Runnable getSoundPlayer(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null");
    }
    return () -> playSoundEffect(name);
  }

}
