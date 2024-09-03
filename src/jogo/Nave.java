package jogo;

import java.awt.*;
import javax.swing.*;

public class Nave extends SpriteQueColide {
	//private static final ImageIcon IMG_NAVE = new ImageIcon("/jogo/Recursos/imagens/nave.png");
	private static final ImageIcon IMG_NAVE = new ImageIcon(Nave.class.getResource("/jogo/Recursos/imagens/nave.png"));
	public static final int LARGURA = IMG_NAVE.getIconWidth();
	public static final int ALTURA = IMG_NAVE.getIconHeight();

	public Nave(int x, int y, int deslocamento) {
		super(x, y, LARGURA, ALTURA, deslocamento);
		esconde();
		Pausa pausa = new Pausa();
		Thread th = new Thread(pausa);
		th.start();
	}

	@Override
	public void atualizarPosicao() {
	}

	@Override
	public void desenhar(Graphics g) {
		if (isVisivel()) {
			int larguraArea = g.getClipBounds().width;
			if (getX() < 0) direita();
			if (getX() > larguraArea - getLargura()) esquerda();
			g.drawImage(IMG_NAVE.getImage(), getX(), getY(), null);
		}
	}

	@Override
	public Rectangle[] retangulosColisao() {
		return new Rectangle[] { 
				new Rectangle(getX() + 35, getY() + 10, 10, 20),
				new Rectangle(getX() + 10, getY() + 30, 60, 30)
		};
	}
	
	class Pausa implements Runnable {
		public void run() {
			try {
				Thread.sleep(1000);
				aparece();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
