package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
	
	public void main(String[] main) {
		String bip = "test.mp3";
		Media hit = new Media(bip);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}

}
