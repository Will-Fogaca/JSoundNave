package teste;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/*
* Teste implementado por Will
* Rotina para testar o aviso de colisão entre retângulos, com o objetivo de orientar o jogador as direções que devem seguir.
* Esse teste não calcula a melhor rota, nem qual o grau de movimento. Apenas calcula a distância entre os retângulos e emite o som.
*
* Notas:
*    A partir desse momento, para emitir o som, primeiro carrego em um InputStream, utilizando o getResources da class, para facilitar na troca dos caminhos.
*    Após carregar o áudio em um InputStream, crio um BufferedStream, pois o InputStream não suporta operações Reset e Mark que o AudioInputStream precisa,
*    já o Buffered sim.
*/

public class TestaAvisoColisao extends JPanel implements ActionListener {
    private Rectangle retangulo1, retangulo2;
    private int velocidade = 5;
    private Timer timer;
    private Clip somProximidade;
    private boolean somTocado = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teste de Aviso de Colisão");
        TestaAvisoColisao painel = new TestaAvisoColisao();
        frame.add(painel);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public TestaAvisoColisao() {
        retangulo1 = new Rectangle(50, 100, 100, 50);
        retangulo2 = new Rectangle(500, 100, 100, 50);
        inicializarSomProximidade();
        timer = new Timer(30, this);
        timer.start();
    }

    public void inicializarSomProximidade() {
        try {
            InputStream inputStream = TestaAvisoColisao.class.getResourceAsStream("/teste/aviso.wav");
            if (inputStream == null) {
                throw new FileNotFoundException("Arquivo de som não encontrado: /teste/aviso.wav");
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            somProximidade = AudioSystem.getClip();
            somProximidade.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(retangulo1.x, retangulo1.y, retangulo1.width, retangulo1.height);

        g.setColor(Color.RED);
        g.fillRect(retangulo2.x, retangulo2.y, retangulo2.width, retangulo2.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!somTocado) {
            retangulo1.x += velocidade;
            retangulo2.x -= velocidade;
            verificarProximidade(retangulo1, retangulo2);
            repaint();
        }
    }

    private void verificarProximidade(Rectangle a, Rectangle b) {
        int zonaProximidade = 50;

        Rectangle areaProximidadeA = new Rectangle(a.x - zonaProximidade, a.y - zonaProximidade,
                a.width + 2 * zonaProximidade, a.height + 2 * zonaProximidade);

        if (areaProximidadeA.intersects(b)) {
            if (somProximidade != null && !somTocado) {
                new Thread(() -> {
                    somProximidade.setFramePosition(0);
                    somProximidade.start();
                }).start();
                somTocado = true;
            }
        }
    }
}
