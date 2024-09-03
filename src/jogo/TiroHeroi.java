package jogo;

public class TiroHeroi extends Tiro {

	public TiroHeroi(int x, int y, int largura, int altura, int deslocamento) {
		super(x, y, largura, altura, deslocamento);
	}

	public TiroHeroi(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void atualizarPosicao() {
		sobe();
	}
}
