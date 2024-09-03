package jogo;

import teste.TestaSom;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicaDeFundo {
	private static AudioInputStream ais;
	private static Clip clip;

	public MusicaDeFundo() {
		try {
			String pathAudio = "/jogo/Recursos/sons/musica-acao.wav";
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
