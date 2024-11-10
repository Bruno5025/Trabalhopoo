package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoradorDAO {

    private Connection connection;

    public MoradorDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para inserir um morador
    public void inserir(Morador morador) {
        String sql = "INSERT INTO Morador (nome, profissao, telefone, endereco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, morador.getNome());
            stmt.setString(2, morador.getProfissao());
            stmt.setString(3, morador.getTelefone());
            stmt.setString(4, morador.getEndereco());
            stmt.executeUpdate();
            System.out.println("Morador inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os moradores
    public List<Morador> listar() {
        List<Morador> moradores = new ArrayList<>();
        String sql = "SELECT * FROM Morador";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Morador morador = new Morador();
                morador.setIdMorador(rs.getInt("id_morador"));
                morador.setNome(rs.getString("nome"));
                morador.setProfissao(rs.getString("profissao"));
                morador.setTelefone(rs.getString("telefone"));
                morador.setEndereco(rs.getString("endereco"));
                moradores.add(morador);
            }
            System.out.println("Moradores listados com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moradores;
    }

    // Método para atualizar um morador
    public void atualizar(Morador morador) {
        String sql = "UPDATE Morador SET nome = ?, profissao = ?, telefone = ?, endereco = ? WHERE id_morador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, morador.getNome());
            stmt.setString(2, morador.getProfissao());
            stmt.setString(3, morador.getTelefone());
            stmt.setString(4, morador.getEndereco());
            stmt.setInt(5, morador.getIdMorador());
            stmt.executeUpdate();
            System.out.println("Morador atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um morador
    public void excluir(int id) {
        String sql = "DELETE FROM Morador WHERE id_morador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Morador excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
