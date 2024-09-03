package jogo;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TelaDeJogo extends JPanel {
	private static final long serialVersionUID = 1L;
	private Fundo fundo;
	private Painel painel;
	private ArrayList<Sprite> sprites;
	
	@SuppressWarnings("unused")
	private TelaDeJogo() {}
	
	public TelaDeJogo(int largura, int altura, Fundo fundo,
			Painel painel, ArrayList<Sprite> sprites) {
		this.fundo = fundo;
		this.painel = painel;
		this.sprites = sprites;

		setPreferredSize(new Dimension(largura, altura));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		fundo.desenhar(g);
		
		for (Sprite s : sprites) {
			s.desenhar(g);
			
			// Desenha retângulos de colisão
/*			if (s instanceof SpriteQueColide) {
				g.setColor(Color.YELLOW);
				SpriteQueColide sc = (SpriteQueColide) s;
				Rectangle[] rets = sc.retangulosColisao();
				for (Rectangle r : rets) {
					g.drawRect(r.x, r.y, r.width, r.height);
				}
			}
*/
		}
		
//		explosao.desenhar(g);
		painel.desenhar(g);
	}
}
