package awktal.mule;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Paths;

public class Music {

    static String mp3_file;
    static Media song;
    static MediaPlayer player;
    static boolean isPlaying = true;

    public Music() {
    }

    /**
     * The musicplayer for the game.
    */
    public static void musicMp3(String playSong) {
        try {
            if (!isPlaying) {
                player.pause();
            }
            isPlaying = false;
            mp3_file = playSong;
            song = new Media(Paths.get(mp3_file).toUri().toString());
            player = new MediaPlayer(song);
            player.play();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}