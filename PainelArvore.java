import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelArvore extends JPanel {
    private ArvoreBinaria arvore;
    private List<No> caminho = null;
    private Timer animador;
    private int passo = 0;

    public PainelArvore(ArvoreBinaria arvore) {
        this.arvore = arvore;
    }

    public void destacarCaminhoAnimado(List<No> caminho) {
        if (caminho == null) return;
        for (No n : caminho) n.ordemVisitado = -1;
        this.caminho = caminho;
        passo = 0;
        animador = new Timer(500, e -> {
            if (passo < caminho.size()) {
                caminho.get(passo).ordemVisitado = passo + 1;
                repaint();
                passo++;
            } else {
                animador.stop();
            }
        });
        animador.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        No raiz = arvore.getRaiz();
        if (raiz != null) {
            calcularPosicoes(raiz, getWidth() / 2, 50, getWidth() / 4);
            desenhar(g, raiz);
        }
    }

    private void calcularPosicoes(No no, int x, int y, int deslocamento) {
        if (no == null) return;
        no.x = x;
        no.y = y;
        calcularPosicoes(no.esquerdo, x - deslocamento, y + 50, deslocamento / 2);
        calcularPosicoes(no.direito, x + deslocamento, y + 50, deslocamento / 2);
    }

    private void desenhar(Graphics g, No no) {
        if (no == null) return;
        g.setColor(Color.BLACK);
        if (no.esquerdo != null)
            g.drawLine(no.x, no.y, no.esquerdo.x, no.esquerdo.y);
        if (no.direito != null)
            g.drawLine(no.x, no.y, no.direito.x, no.direito.y);

        if (no.ordemVisitado != -1) {
            g.setColor(Color.ORANGE);
            g.fillOval(no.x - 20, no.y - 20, 40, 40);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(no.ordemVisitado), no.x - 5, no.y - 25);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(no.x - 20, no.y - 20, 40, 40);
        }

        g.setColor(Color.BLACK);
        g.drawOval(no.x - 20, no.y - 20, 40, 40);
        g.drawString(String.valueOf(no.valor), no.x - 5, no.y + 5);

        desenhar(g, no.esquerdo);
        desenhar(g, no.direito);
    }
}