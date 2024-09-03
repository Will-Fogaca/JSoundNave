package teste;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoRetanguloCrescente extends JFrame {
	private static final long serialVersionUID = 1L;
	private TelaRetangulo tela;
	private JButton btn;
	int delay = 50; // milliseconds
	ActionListener taskPerformer; 
	Timer timer;
	
	int largura; int altura;
	
	public JogoRetanguloCrescente() {
		tela = new TelaRetangulo();
		btn = new JButton("Parar");
		add(tela, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		btn.addActionListener(new OuvinteBotao());
		
		taskPerformer = new OuvinteAnimacao();
		timer = new Timer(delay, taskPerformer);
		timer.start();

	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new JogoRetanguloCrescente();
            }
        });
	}
	
	private class OuvinteAnimacao implements ActionListener{
		public void actionPerformed(ActionEvent evt) {
			tela.setPosicaoTamanho(0, 0, largura, altura);
			tela.repaint();
			largura += 10; altura += 10;
			if (altura > tela.getHeight()) {
				largura = 1;
				altura = 1;
			}
		}
	}
	
	private class OuvinteBotao implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (timer.isRunning()) {
				timer.stop();
				btn.setText("Animar");
			} else {
				timer.restart();
				btn.setText("Pausar");
			}
		}
	}
}
