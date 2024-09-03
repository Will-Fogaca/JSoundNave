package jogo;

import java.awt.Graphics;

public abstract class Sprite {
	private int x;
	private int y;
	private int largura;
	private int altura;
	private int deslocamento;
	private boolean ativo;
	private boolean visivel;
	
	public Sprite(int x, int y, int largura, int altura, int deslocamento) {
		super();
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.deslocamento = deslocamento;
		ativo = true;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	public int getDeslocamento() {
		return deslocamento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void desativa() {
		ativo = false;
	}
	
	public boolean isVisivel() {
		return visivel;
	}
	
	public void aparece() {
		visivel = true;
	}
	
	public void esconde() {
		visivel = false;
	}
	
	public void desce() {
		if (!ativo) return;
		y += deslocamento;
	}
	
	public void sobe() {
		if (!ativo) return;
		y -= deslocamento;
	}
	
	public void direita() {
		if (!ativo) return;
		x += deslocamento;
	}
	
	public void esquerda() {
		if (!ativo) return;
		x -= deslocamento;
	}
	
	public void moverPara(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void atualizarPosicao();
	
	public abstract void desenhar(Graphics g);
}
