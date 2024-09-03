package jogo;

import java.awt.*;
import javax.swing.*;

public class Inimigo extends SpriteQueColide {
	private static final ImageIcon IMG_INIMIGO = new ImageIcon(Inimigo.class.getResource("/jogo/Recursos/imagens/inimigo.png"));
	public static final int LARGURA = IMG_INIMIGO.getIconWidth();
	public static final int ALTURA = IMG_INIMIGO.getIconHeight();

	public Inimigo(int x, int y, int deslocamento) {
		super(x, y, LARGURA, ALTURA, deslocamento);
	}

	@Override
	public void atualizarPosicao() {
		desce();
	}

	@Override
	public void desenhar(Graphics g) {
		int alturaArea = g.getClipBounds().height;
		if (getY() > alturaArea) {
			desativa();
			return;
		}
		g.drawImage(IMG_INIMIGO.getImage(), getX(), getY(), null);
	}
	
	@Override
	public Rectangle[] retangulosColisao() {
		return new Rectangle[] { 
				new Rectangle(getX() + 10, getY(), getLargura() - 20, getAltura() - 20)
		};
	}
}
