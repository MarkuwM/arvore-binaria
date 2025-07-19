import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ArvoreGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArvoreBinaria arvore = new ArvoreBinaria();
            PainelArvore painel = new PainelArvore(arvore);

            JFrame frame = new JFrame("Árvore Binária de Busca");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JTextField campoValor = new JTextField(5);
            JButton btnInserir = new JButton("Inserir");
            JButton btnRemover = new JButton("Remover");
            JButton btnBuscar = new JButton("Buscar");
            JButton btnDFS = new JButton("DFS");
            JButton btnBFS = new JButton("BFS");
            JTextArea saida = new JTextArea(3, 40);
            saida.setEditable(false);

            JPanel painelControles = new JPanel();
            painelControles.add(new JLabel("Valor:"));
            painelControles.add(campoValor);
            painelControles.add(btnInserir);
            painelControles.add(btnRemover);
            painelControles.add(btnBuscar);
            painelControles.add(btnDFS);
            painelControles.add(btnBFS);

            btnInserir.addActionListener(e -> {
                try {
                    int v = Integer.parseInt(campoValor.getText());
                    arvore.inserir(v);
                    painel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnRemover.addActionListener(e -> {
                try {
                    int v = Integer.parseInt(campoValor.getText());
                    arvore.remover(v);
                    painel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnBuscar.addActionListener(e -> {
                try {
                    int v = Integer.parseInt(campoValor.getText());
                    No n = arvore.buscar(v);
                    saida.setText(n != null ? "Valor encontrado: " + v : "Valor não encontrado");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnDFS.addActionListener(e -> {
                try {
                    int v = Integer.parseInt(campoValor.getText());
                    List<No> caminho = arvore.caminhoDFS(v);
                    painel.destacarCaminhoAnimado(caminho);
                    saida.setText("Caminho DFS: " + caminho.stream().map(n -> n.valor + "").reduce((a, b) -> a + " -> " + b).orElse(""));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnBFS.addActionListener(e -> {
                try {
                    int v = Integer.parseInt(campoValor.getText());
                    List<No> caminho = arvore.caminhoBFS(v);
                    painel.destacarCaminhoAnimado(caminho);
                    saida.setText("Caminho BFS: " + caminho.stream().map(n -> n.valor + "").reduce((a, b) -> a + " -> " + b).orElse(""));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.setLayout(new BorderLayout());
            frame.add(painelControles, BorderLayout.NORTH);
            frame.add(painel, BorderLayout.CENTER);
            frame.add(new JScrollPane(saida), BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}