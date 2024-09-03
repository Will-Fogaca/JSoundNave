package jogo;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Fundo {
	java.util.List<ImgFundo> itensFundo = new ArrayList<>();

	public Fundo(int altura, int deslocamento) {
		itensFundo.add(
				new ImgFundo(new ImageIcon(Fundo.class.getResource("/jogo/Recursos/imagens/espaco.png")), 2));
		itensFundo.add(
				new ImgFundo(new ImageIcon(Fundo.class.getResource("/jogo/Recursos/imagens/estrelas.png")), 10));
		itensFundo.add(
				new ImgFundo(new ImageIcon(Fundo.class.getResource("/jogo/Recursos/imagens/nuvens.png")), 7));
	}
	/*public Fundo(int altura, int deslocamento) {
		itensFundo.add(
				new ImgFundo(new ImageIcon("/jogo/Recursos/imagens/espaco.png"), 2));
		itensFundo.add(
				new ImgFundo(new ImageIcon("/jogo/Recursos/imagens/estrelas.png"), 10));
		itensFundo.add(
				new ImgFundo(new ImageIcon("/jogo/Recursos/imagens/nuvens.png"), 7));
	}*/

	public void atualizarPosicao() {
		for (ImgFundo itemFundo : itensFundo ) {
			itemFundo.atualizarPosicao();
		}
	}

	public void desenhar(Graphics g) {
		for (ImgFundo itemFundo : itensFundo ) {
			int altura = itemFundo.imagem.getIconHeight();
			g.drawImage(itemFundo.imagem.getImage(), 0, 
					itemFundo.posEmenda - altura, null);
			g.drawImage(itemFundo.imagem.getImage(), 0, 
					itemFundo.posEmenda, null);
		}
	}
	
	private class ImgFundo {
		ImageIcon imagem;
		int deslocamento;
		int posEmenda;
		
		ImgFundo(ImageIcon imagem, int deslocamento){
			this.imagem = imagem;
			this.deslocamento = deslocamento;
			this.posEmenda = 0;
		}
		
		void atualizarPosicao() {
			posEmenda += deslocamento;
			if (posEmenda > imagem.getIconHeight()) {
				posEmenda = 0;
			}
		}
	}
}
