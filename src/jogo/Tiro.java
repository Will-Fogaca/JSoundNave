package jogo;

import teste.TestaSom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.sampled.*;

public abstract class Tiro extends SpriteQueColide {
	private static AudioInputStream ais;
	private static Clip clip;
	
	public Tiro(int x, int y, int largura, int altura, int deslocamento) {
		super(x, y, largura, altura, deslocamento);

		try {
			String pathAudio = "/jogo/Recursos/sons/tiro.wav";
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
	
	public Tiro(int x, int y) {
		this(x, y, 4, 10, 50);
	}

	@Override
	public void desenhar(Graphics g) {
		int alturaArea = g.getClipBounds().height;
		if (getY() < 0 || getY() > alturaArea ) {
			desativa();
		} else {
			g.setColor(Color.YELLOW);
			g.fillRect(getX(), getY(), getLargura(), getAltura());
		}
	}
	
	@Override
	public Rectangle[] retangulosColisao() {
		return new Rectangle[] { 
				new Rectangle(getX(), getY(), getLargura(), getAltura())
		};
	}
}
