package gui;

import models.Lote;
import models.LoteDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestaoLotes extends JFrame {
    private JTextField tamanhoField, numeroLoteField, idMoradorField;
    private JButton adicionarButton, listarButton, atualizarButton, excluirButton, voltarButton;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private LoteDAO loteDAO = new LoteDAO();

    public GestaoLotes() {
        setTitle("Gestão de Lotes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite voltar ao menu principal
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para os campos de entrada
        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 5, 5));
        painelCampos.add(new JLabel("Tamanho:"));
        tamanhoField = new JTextField();
        painelCampos.add(tamanhoField);

        painelCampos.add(new JLabel("Número do Lote:"));
        numeroLoteField = new JTextField();
        painelCampos.add(numeroLoteField);

        painelCampos.add(new JLabel("ID do Morador:"));
        idMoradorField = new JTextField();
        painelCampos.add(idMoradorField);

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

        // Tabela para exibir os lotes
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Tamanho", "Número Lote", "ID Morador"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(580, 150));
        add(scrollPane, BorderLayout.SOUTH);

        // Ações dos botões
        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarLote();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarLotes();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarLote();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirLote();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new MainMenuGUI().setVisible(true); // Abre o menu principal novamente
            }
        });
    }

    private void adicionarLote() {
        double tamanho = Double.parseDouble(tamanhoField.getText());
        int numeroLote = Integer.parseInt(numeroLoteField.getText());
        int idMorador = Integer.parseInt(idMoradorField.getText());

        Lote lote = new Lote();
        lote.setTamanho(tamanho);
        lote.setNumeroLote(numeroLote);
        lote.setIdMorador(idMorador);
        loteDAO.inserir(lote);
        JOptionPane.showMessageDialog(this, "Lote adicionado com sucesso!");
        listarLotes();
    }

    private void listarLotes() {
        modeloTabela.setRowCount(0);
        List<Lote> lotes = loteDAO.listar();
        for (Lote l : lotes) {
            modeloTabela.addRow(new Object[]{l.getIdLote(), l.getTamanho(), l.getNumeroLote(), l.getIdMorador()});
        }
    }

    private void atualizarLote() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            double tamanho = Double.parseDouble(tamanhoField.getText());
            int numeroLote = Integer.parseInt(numeroLoteField.getText());
            int idMorador = Integer.parseInt(idMoradorField.getText());

            Lote lote = new Lote(id, tamanho, numeroLote, idMorador);
            loteDAO.atualizar(lote);
            JOptionPane.showMessageDialog(this, "Lote atualizado com sucesso!");
            listarLotes();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um lote para atualizar.");
        }
    }

    private void excluirLote() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            loteDAO.excluir(id);
            JOptionPane.showMessageDialog(this, "Lote excluído com sucesso!");
            listarLotes();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um lote para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GestaoLotes().setVisible(true);
            }
        });
    }
}
