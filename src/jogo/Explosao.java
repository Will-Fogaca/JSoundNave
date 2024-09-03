package jogo;

import teste.TestaSom;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

class Explosao extends Sprite {
	private static AudioInputStream ais;
	private static Clip clip;
	private static final ImageIcon imgExplosao = new ImageIcon("/jogo/Recursos/imagens/explosao.png");
	private static final int TOTAL_QUADROS = 5;
	private static final int largura = imgExplosao.getIconWidth() / TOTAL_QUADROS;
	private static final int altura = imgExplosao.getIconHeight();
	private javax.swing.Timer timerExplosao;
	private boolean ativo;
	private int quadro;
	
	Explosao(int x, int y) {
		super(x, y, largura, altura, 0);
		AcaoExplosao acao = new AcaoExplosao();
		timerExplosao = new javax.swing.Timer(100, acao);
		ativo = true;
		try {
			String pathAudio = "/jogo/Recursos/sons/explosao.wav";
			InputStream input = TestaSom.class.getResourceAsStream(pathAudio);
			if (input == null) {
				throw new FileNotFoundException("File not found: " + pathAudio);
			}

			InputStream bufferedIn = new BufferedInputStream(input);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.setFramePosition(0);
			clip.start();
			timerExplosao.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void desenhar(Graphics g) {
		if (ativo) {
			g.drawImage(imgExplosao.getImage(), 
					getX(), getY(), getX() + largura, getY() + largura,
					quadro * largura, 0,
					quadro * largura + largura, altura,
					null);
		}
	}
	
	class AcaoExplosao implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			quadro++;
			if (quadro == TOTAL_QUADROS) {
				quadro = 0;
				ativo = false;
				timerExplosao.stop();
			}
		}
	}

	@Override
	public void atualizarPosicao() {
	}
}