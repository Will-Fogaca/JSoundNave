package teste;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TestaSom {
	public static void main(String[] args) {
		try {
			String pathAudio = "/jogo/Recursos/sons/explosao.wav";
			InputStream input = TestaSom.class.getResourceAsStream(pathAudio);
			if (input == null) {
				throw new FileNotFoundException("File not found: " + pathAudio);
			}

			InputStream bufferedIn = new BufferedInputStream(input);
			AudioInputStream as = AudioSystem.getAudioInputStream(bufferedIn);
			Clip clip = AudioSystem.getClip();
			clip.open(as);
			clip.setFramePosition(0);
			clip.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
