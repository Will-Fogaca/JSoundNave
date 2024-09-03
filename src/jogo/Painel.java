package jogo;

import java.awt.*;

public class Painel {
	private final Font FONT = new Font("SansSerif", Font.BOLD, 20); 
	private int vidas;
	private int pontos;
	
	@SuppressWarnings("unused")
	private Painel() {}
	
	public Painel(int vidas, int pontos) {
		this.vidas = vidas;
		this.pontos = pontos;
	}

	public int getVidas() {
		return vidas;
	}

	public int getPontos() {
		return pontos;
	}
	
	public void pontuar(int pontos) {
		this.pontos += pontos;
	}
	
	public void aumentarVidas() {
		vidas++;
	}
	
	public void diminuirVidas() {
		vidas--;
	}

	public void desenhar(Graphics g) {
		g.setColor(Color.BLUE);
		g.setFont(FONT);
		g.drawString("Vidas: " + vidas + " - Pontos: " + pontos, 5, 20);
		
		if (vidas < 0) {
			g.drawString("Game Over", 5, 200);
		}
	}
}
