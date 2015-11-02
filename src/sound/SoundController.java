package sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundController {
	private static boolean loop;
	private static AudioStream stream;

	private static void setAudio(File file) throws IOException {
		stream = new AudioStream(new FileInputStream(file));
		AudioPlayer.player.start(stream);
	}

	public static void playAudio(File file, boolean loopBoolean)
			throws InterruptedException, IOException {
		loop = loopBoolean;
		setAudio(file);
		while (loop) {
			if (stream.available() == 0) {
				System.out.println("Looping...");
				setAudio(file);
			}
		}
	}
	
	public static void playAudio(boolean loopBoolean) throws InterruptedException, IOException {
		playAudio(new File("130-bpm-electro-synth-loop.wav"), loopBoolean);
	}

	public static void stopAudio(File file) throws IOException {
		loop = false;
		AudioPlayer.player.stop(new AudioStream(new FileInputStream(file)));
	}

}
