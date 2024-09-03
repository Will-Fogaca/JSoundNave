package jogo;

public class TiroInimigo extends Tiro {

	public TiroInimigo(int x, int y, int largura, int altura, int deslocamento) {
		super(x, y, largura, altura, deslocamento);
	}

	public TiroInimigo(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void atualizarPosicao() {
		desce();
	}
}
