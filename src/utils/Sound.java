package utils;

/**
 * The Sound enum represents different sound resources used in the application.
 * Each enum constant corresponds to a specific audio file, identified by its file path.
 */
public enum Sound {
    GAME_MUSIC("resources/sounds/game_music.wav"),
    BUTTON_CLICK("resources/sounds/btn_click.wav"),
    PUCK_SOUND("resources/sounds/puck_sound.wav");

    private final String filePath;

    /**
     * Constructs a Sound enum instance with the specified file path.
     *
     * @param filePath The file path corresponding to the audio resource.
     */
    Sound(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the file path associated with the current instance.
     *
     * @return the file path as a String
     */
    public String getFilePath() {
        return filePath;
    }
}
