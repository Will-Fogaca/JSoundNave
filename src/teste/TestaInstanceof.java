package teste;

import jogo.*;


public class TestaInstanceof {
	public static void main(String[] args) {
		Sprite nave = new Nave(0, 0, 10);
		Sprite inimigo = new Inimigo(0 , 0, 10);
		
		System.out.println("Nave � inst�ncia de Sprite: " 
				+ (nave instanceof SpriteQueColide));
		System.out.println("Inimigo � inst�ncia de Sprite: " 
				+ (inimigo instanceof SpriteQueColide));
	}
}
