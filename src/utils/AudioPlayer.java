package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * AudioPlayer is a utility class designed to handle basic audio playback operations,
 * such as playing sounds, looping them continuously, and stopping playback.
 * It leverages the javax.sound.sampled package to manage audio resources.
 */
public class AudioPlayer {
    private static Clip clip;

    /**
     * Plays the specified audio file associated with the given {@code Sound} instance.
     * The method retrieves the file path from the provided {@code Sound} object,
     * creates an audio stream, and starts playback.
     *
     * @param sound The {@code Sound} instance representing the audio file to be played.
     */
    public static void play(Sound sound) {
        try {
            File audioFile = new File(sound.getFilePath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip newClip = AudioSystem.getClip();
            newClip.open(audioStream);
            newClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error of reading : " + e.getMessage());
        }
    }

    /**
     * Plays the specified audio file associated with the given {@code Sound} instance
     * in a continuous loop. The audio file is retrieved via the {@code getFilePath}
     * method of the {@code Sound} instance. If a sound is already playing, it is stopped
     * before starting the new loop.
     *
     * @param sound The {@code Sound} instance representing the audio file to be played
     *              in a loop.
     */
    public static void loop(Sound sound) {
        stop(); // We stop the last sound
        try {
            File audioFile = new File(sound.getFilePath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error of reading : " + e.getMessage());
        }
    }

    /**
     * Stops the currently playing audio clip if there is one active.
     * This method checks if the audio clip is not null and is currently running.
     * If so, the playback is stopped, and the clip is closed to release resources.
     */
    public static void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    /**
     * Checks if the audio clip is currently playing.
     * This method verifies whether the clip is not null and is actively running.
     *
     * @return {@code true} if an audio clip is currently playing; {@code false} otherwise
     */
    public static boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
