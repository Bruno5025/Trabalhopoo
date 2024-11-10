package gui;

import models.Morador;
import models.MoradorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MoradorGUI extends JFrame {
    private JTextField nomeField, profissaoField, telefoneField, enderecoField;
    private JButton adicionarButton, listarButton, atualizarButton, excluirButton, voltarButton;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private MoradorDAO moradorDAO = new MoradorDAO();

    public MoradorGUI() {
        setTitle("Gestão de Moradores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite retornar ao menu principal
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para os campos de entrada
        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
        painelCampos.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        painelCampos.add(nomeField);

        painelCampos.add(new JLabel("Profissão:"));
        profissaoField = new JTextField();
        painelCampos.add(profissaoField);

        painelCampos.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        painelCampos.add(telefoneField);

        painelCampos.add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        painelCampos.add(enderecoField);

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

        // Tabela para exibir os moradores
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Profissão", "Telefone", "Endereço"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(580, 150));
        add(scrollPane, BorderLayout.SOUTH);

        // Ações dos botões
        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarMorador();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarMoradores();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarMorador();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirMorador();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new MainMenuGUI().setVisible(true); // Abre o menu principal novamente
            }
        });
    }

    private void adicionarMorador() {
        String nome = nomeField.getText();
        String profissao = profissaoField.getText();
        String telefone = telefoneField.getText();
        String endereco = enderecoField.getText();

        Morador morador = new Morador();
        morador.setNome(nome);
        morador.setProfissao(profissao);
        morador.setTelefone(telefone);
        morador.setEndereco(endereco);
        moradorDAO.inserir(morador);
        JOptionPane.showMessageDialog(this, "Morador adicionado com sucesso!");
        listarMoradores();
    }

    private void listarMoradores() {
        modeloTabela.setRowCount(0);
        List<Morador> moradores = moradorDAO.listar();
        for (Morador m : moradores) {
            modeloTabela.addRow(new Object[]{m.getIdMorador(), m.getNome(), m.getProfissao(), m.getTelefone(), m.getEndereco()});
        }
    }

    private void atualizarMorador() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            String nome = nomeField.getText();
            String profissao = profissaoField.getText();
            String telefone = telefoneField.getText();
            String endereco = enderecoField.getText();

            Morador morador = new Morador(id, nome, profissao, telefone, endereco);
            moradorDAO.atualizar(morador);
            JOptionPane.showMessageDialog(this, "Morador atualizado com sucesso!");
            listarMoradores();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um morador para atualizar.");
        }
    }

    private void excluirMorador() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            moradorDAO.excluir(id);
            JOptionPane.showMessageDialog(this, "Morador excluído com sucesso!");
            listarMoradores();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um morador para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoradorGUI().setVisible(true);
            }
        });
    }
}
