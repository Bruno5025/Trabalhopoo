package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JFrame {

    public MainMenuGUI() {
        setTitle("Menu Principal - Horta Comunitária");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título do Menu Principal
        JLabel titulo = new JLabel("Menu Principal", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // Painel central para os botões
        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton moradorButton = new JButton("Gestão de Moradores");
        JButton loteButton = new JButton("Gestão de Lotes");
        JButton plantaButton = new JButton("Gestão de Plantas");
        JButton relatorioButton = new JButton("Relatórios e Estatísticas");

        // Estilo dos botões
        Font botaoFont = new Font("Arial", Font.PLAIN, 18);
        moradorButton.setFont(botaoFont);
        loteButton.setFont(botaoFont);
        plantaButton.setFont(botaoFont);
        relatorioButton.setFont(botaoFont);

        painelBotoes.add(moradorButton);
        painelBotoes.add(loteButton);
        painelBotoes.add(plantaButton);
        painelBotoes.add(relatorioButton);

        add(painelBotoes, BorderLayout.CENTER);

        // Ações dos botões
        moradorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MoradorGUI().setVisible(true);
            }
        });

        loteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GestaoLotes().setVisible(true);
            }
        });

        plantaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PlantaGUI().setVisible(true);
            }
        });

        relatorioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RelatorioGUI().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenuGUI().setVisible(true);
            }
        });
    }
}
