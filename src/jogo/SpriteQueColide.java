package jogo;

import java.awt.Rectangle;

public abstract class SpriteQueColide extends Sprite {

	public SpriteQueColide(int x, int y, int largura, int altura, int deslocamento) {
		super(x, y, largura, altura, deslocamento);
	}

	public abstract Rectangle[] retangulosColisao();
	
	public boolean colidiuCom(SpriteQueColide outro) {
		for (Rectangle a : retangulosColisao()) {
			for (Rectangle b : outro.retangulosColisao()) {
				if (colidiu(a, b)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean colidiu(Rectangle a, Rectangle b) {
		int xMaisLarguraA = a.x + a.width;
		int xMaisLarguraB = b.x + b.width;
		int yMaisAlturaA = a.y + a.height;
		int yMaisAlturaB = b.y + b.height;
		if (xMaisLarguraA > b.x 
				&& a.x < xMaisLarguraB 
				&& yMaisAlturaA > b.y 
				&& a.y < yMaisAlturaB) {
			return true;
		}
		return false;
	}
}
