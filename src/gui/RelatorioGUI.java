package gui;

import models.RelatorioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatorioGUI extends JFrame {
    private JLabel totalMoradoresLabel, totalLotesLabel, totalPlantasLabel;
    private RelatorioDAO relatorioDAO;

    public RelatorioGUI() {
        relatorioDAO = new RelatorioDAO();

        setTitle("Relatórios e Estatísticas da Horta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel tituloLabel = new JLabel("Relatórios da Horta");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(tituloLabel);

        painelPrincipal.add(Box.createVerticalStrut(20));

        totalMoradoresLabel = new JLabel();
        totalMoradoresLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        painelPrincipal.add(totalMoradoresLabel);

        totalLotesLabel = new JLabel();
        totalLotesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        painelPrincipal.add(totalLotesLabel);

        totalPlantasLabel = new JLabel();
        totalPlantasLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        painelPrincipal.add(totalPlantasLabel);

        add(painelPrincipal, BorderLayout.CENTER);

        // Botão de atualização e voltar
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarRelatorios();
            }
        });
        painelBotoes.add(atualizarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        painelBotoes.add(voltarButton);

        add(painelBotoes, BorderLayout.SOUTH);

        // Atualizar os relatórios ao abrir a interface
        atualizarRelatorios();
    }

    private void atualizarRelatorios() {
        int totalMoradores = relatorioDAO.contarMoradores();
        int totalLotes = relatorioDAO.contarLotes();
        int totalPlantas = relatorioDAO.contarPlantas();

        totalMoradoresLabel.setText("Total de Moradores: " + totalMoradores);
        totalLotesLabel.setText("Total de Lotes: " + totalLotes);
        totalPlantasLabel.setText("Total de Plantas: " + totalPlantas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RelatorioGUI().setVisible(true);
            }
        });
    }
}
