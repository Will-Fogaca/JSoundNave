package teste;

import javax.swing.*;
import java.awt.*;

public class TelaRetangulo extends JPanel {
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int largura;
	private int altura;

	public void setPosicaoTamanho(int x, int y, int largura, int altura) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(x, y, largura, altura);
	}
}
