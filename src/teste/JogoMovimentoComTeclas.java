package teste;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class JogoMovimentoComTeclas extends JFrame {
	private static final long serialVersionUID = 1L;
	private TelaRetangulo tela;
	int delay = 50; // milliseconds
	ActionListener taskPerformer;
	Timer timer;

	int x, y, largura, altura, deslocamento;

	public JogoMovimentoComTeclas() {
		tela = new TelaRetangulo();
		add(tela, BorderLayout.CENTER);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		largura = altura = 50;
		x = (tela.getWidth() - largura) / 2;
		y = (tela.getHeight() - altura) / 2;
		deslocamento = 20;

		taskPerformer = new OuvinteAnimacao();
		timer = new Timer(delay, taskPerformer);
		timer.start();
		
		addKeyListener(new OuvinteTeclado());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JogoMovimentoComTeclas();
			}
		});
	}

	private class OuvinteAnimacao implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			tela.setPosicaoTamanho(x, y, largura, altura);
			tela.repaint();
		}
	}

	private class OuvinteTeclado extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				y -= deslocamento;
				y = Math.max(y, 0);
				break;
			case KeyEvent.VK_DOWN:
				y += deslocamento;
				y = Math.min(y, tela.getHeight() - altura);
				break;
			case KeyEvent.VK_LEFT:
				x -= deslocamento;
				x = Math.max(x, 0);
				break;
			case KeyEvent.VK_RIGHT:
				x += deslocamento;
				x = Math.min(x, tela.getWidth() - largura);
				break;
			}
		}
	}
}
