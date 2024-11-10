package gui;

import models.Planta;
import models.PlantaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class PlantaGUI extends JFrame {
    private JTextField nomeField, dataPlantioField, dataColheitaField, idLoteField;
    private JButton adicionarButton, listarButton, atualizarButton, excluirButton, voltarButton;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private PlantaDAO plantaDAO = new PlantaDAO();

    public PlantaGUI() {
        setTitle("Gestão de Plantas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite retornar ao menu principal
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para os campos de entrada
        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
        painelCampos.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        painelCampos.add(nomeField);

        painelCampos.add(new JLabel("Data Plantio (AAAA-MM-DD):"));
        dataPlantioField = new JTextField();
        painelCampos.add(dataPlantioField);

        painelCampos.add(new JLabel("Data Colheita (AAAA-MM-DD):"));
        dataColheitaField = new JTextField();
        painelCampos.add(dataColheitaField);

        painelCampos.add(new JLabel("ID do Lote:"));
        idLoteField = new JTextField();
        painelCampos.add(idLoteField);

        add(painelCampos, BorderLayout.NORTH);

        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        adicionarButton = new JButton("Adicionar");
        listarButton = new JButton("Listar");
        atualizarButton = new JButton("Atualizar");
        excluirButton = new JButton("Excluir");
        voltarButton = new JButton("Voltar ao Menu Principal"); // Botão para retornar ao menu principal

        painelBotoes.add(adicionarButton);
        painelBotoes.add(listarButton);
        painelBotoes.add(atualizarButton);
        painelBotoes.add(excluirButton);
        painelBotoes.add(voltarButton); // Adiciona o botão "Voltar"

        add(painelBotoes, BorderLayout.CENTER);

        // Tabela para exibir as plantas
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Data Plantio", "Data Colheita", "ID Lote"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(580, 150));
        add(scrollPane, BorderLayout.SOUTH);

        // Ações dos botões
        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarPlanta();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarPlantas();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarPlanta();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirPlanta();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new MainMenuGUI().setVisible(true); // Abre o menu principal novamente
            }
        });
    }

    private void adicionarPlanta() {
        String nome = nomeField.getText();
        LocalDate dataPlantio = LocalDate.parse(dataPlantioField.getText());
        LocalDate dataColheita = LocalDate.parse(dataColheitaField.getText());
        int idLote = Integer.parseInt(idLoteField.getText());

        Planta planta = new Planta();
        planta.setNome(nome);
        planta.setDataPlantio(dataPlantio);
        planta.setDataColheita(dataColheita);
        planta.setIdLote(idLote);
        plantaDAO.inserir(planta);
        JOptionPane.showMessageDialog(this, "Planta adicionada com sucesso!");
        listarPlantas();
    }

    private void listarPlantas() {
        modeloTabela.setRowCount(0);
        List<Planta> plantas = plantaDAO.listar();
        for (Planta p : plantas) {
            modeloTabela.addRow(new Object[]{p.getIdPlanta(), p.getNome(), p.getDataPlantio(), p.getDataColheita(), p.getIdLote()});
        }
    }

    private void atualizarPlanta() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            String nome = nomeField.getText();
            LocalDate dataPlantio = LocalDate.parse(dataPlantioField.getText());
            LocalDate dataColheita = LocalDate.parse(dataColheitaField.getText());
            int idLote = Integer.parseInt(idLoteField.getText());

            Planta planta = new Planta(id, nome, dataPlantio, dataColheita, idLote);
            plantaDAO.atualizar(planta);
            JOptionPane.showMessageDialog(this, "Planta atualizada com sucesso!");
            listarPlantas();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma planta para atualizar.");
        }
    }

    private void excluirPlanta() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            plantaDAO.excluir(id);
            JOptionPane.showMessageDialog(this, "Planta excluída com sucesso!");
            listarPlantas();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma planta para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PlantaGUI().setVisible(true);
            }
        });
    }
}
