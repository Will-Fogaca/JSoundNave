package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Jogo extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private Fundo fundo;
	private Painel painel;
	private TelaDeJogo tela;
	private Nave nave;
	private ActionListener gameLoop, criaInimigos;
	private Timer timerGameLoop, timerCriaInimigos;
	
	private final int VIDAS_INICIAL = 3;
	private final int PONTOS_INICIAL = 0;
	private final int LARGURA_TELA = 500;
	private final int ALTURA_TELA = 500;
	private final int DESLOCAMENTO_NAVE = 50;
	private final int ALTURA_FUNDO = 1000;
	
	public Jogo() {
		fundo = new Fundo(ALTURA_FUNDO, 10);
		painel = new Painel(VIDAS_INICIAL, PONTOS_INICIAL);
		tela = new TelaDeJogo(LARGURA_TELA, ALTURA_TELA, fundo, painel, sprites);
		add(tela, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		nave = criaNave(DESLOCAMENTO_NAVE);

		addKeyListener(new OuvinteTeclado());
		
		gameLoop = new GameLoop();
		timerGameLoop = new Timer(50, gameLoop);
		timerGameLoop.start();
		adicionaSprite(new Explosao(LARGURA_TELA / 2 , ALTURA_TELA / 2));

		new MusicaDeFundo();
		
		criaInimigos = new CriaInimigos();
		timerCriaInimigos = new Timer(2000, criaInimigos);
		timerCriaInimigos.restart();
	}

	private Nave criaNave(int deslocamento) {
		int xNave = (tela.getWidth() - Nave.LARGURA) / 2;
		int yNave = tela.getHeight() - Nave.ALTURA;
		nave = new Nave(xNave, yNave, deslocamento);
		adicionaSprite(nave);
		return nave;
	}
	
	public void adicionaSprite(Sprite s) {
		sprites.add(s);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new Jogo();
            }
        });
	}
	
	private class GameLoop implements ActionListener{
		public void actionPerformed(ActionEvent evt) {
			atualizarPosicoes();
			verificarColisoes();
			removeSprites();
			tela.repaint();
		}
		
		private void atualizarPosicoes() {
			for (Sprite s : sprites) {
				s.atualizarPosicao();
			}
			
			fundo.atualizarPosicao();
		}
		
		private void verificarColisoes() {
			int qtdSprites = sprites.size();
			for (int x = 0; x < qtdSprites - 1; x++) {
				Sprite spriteX = sprites.get(x);
				if (!(spriteX instanceof SpriteQueColide)) {
					continue;
				}
				for (int y = x + 1; y < qtdSprites; y++) {
					Sprite spriteY = sprites.get(y);
					if (!(spriteY instanceof SpriteQueColide)) {
						continue;
					}
					SpriteQueColide sX = (SpriteQueColide) spriteX;
					SpriteQueColide sY = (SpriteQueColide) spriteY;
					if (sX.colidiuCom(sY)) {
						tratarColisao(sX, sY);
						return;
					}
				}
			}
		}
		
		private void tratarColisao(Sprite a, Sprite b) {
			if (a instanceof Inimigo && b instanceof TiroHeroi 
					|| a instanceof TiroHeroi && b instanceof Inimigo) {
				a.desativa();
				b.desativa();
				adicionaSprite(new Explosao(a.getX() , a.getY()));
				painel.pontuar(1);
			} else if (a instanceof Inimigo && b instanceof Nave 
					|| a instanceof Nave && b instanceof Inimigo) {
				a.desativa();
				b.desativa();
				adicionaSprite(new Explosao(a.getX() , a.getY()));
				adicionaSprite(new Explosao(b.getX() , b.getY()));
				painel.diminuirVidas();
				if (painel.getVidas() >= 0) {
					nave = criaNave(DESLOCAMENTO_NAVE);
				}
			}
		}
		
		// considerar criar uma lista com sprites a serem removidos
		private void removeSprites() {
			Iterator<Sprite> i = sprites.iterator();
			while (i.hasNext()) {
				Sprite s = i.next();
				if (!s.isAtivo()) {
					i.remove();
				}
			}
		}	
	}
	
	private class CriaInimigos implements ActionListener{
		public void actionPerformed(ActionEvent evt) {
			criaInimigo();
		}
		
		private void criaInimigo() {
			int xInimigo = (int) (Math.random() * (tela.getWidth() - Inimigo.LARGURA));
			int deslocamentoInimigo = (int) (Math.random() * 15 + 5);
			adicionaSprite(new Inimigo(xInimigo , -Inimigo.ALTURA,
					deslocamentoInimigo));
		}
	}
	
	private class OuvinteTeclado extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				if (timerGameLoop.isRunning()) {
					timerGameLoop.stop();
					timerCriaInimigos.stop();
				} else {
					timerGameLoop.restart();
					timerCriaInimigos.restart();
				}
				break;
			case KeyEvent.VK_LEFT:
				nave.esquerda();
				break;
			case KeyEvent.VK_RIGHT:
				nave.direita();
				break;
			case KeyEvent.VK_SPACE:
				int xTiro = nave.getX() + Nave.LARGURA / 2 - 2;
				int yTiro = nave.getY() + 30;
				adicionaSprite(new TiroHeroi(xTiro , yTiro));
			}
		}
	}
}
