package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoteDAO {

    private final Connection connection;

    public LoteDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para inserir um lote
    public void inserir(Lote lote) {
        String sql = "INSERT INTO Lote (tamanho, numero_lote, id_morador) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, lote.getTamanho());
            stmt.setInt(2, lote.getNumeroLote());
            stmt.setInt(3, lote.getIdMorador());
            stmt.executeUpdate();
            System.out.println("Lote inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os lotes
    public List<Lote> listar() {
        List<Lote> lotes = new ArrayList<>();
        String sql = "SELECT * FROM Lote";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Lote lote = new Lote();
                lote.setIdLote(rs.getInt("id_lote"));
                lote.setTamanho(rs.getDouble("tamanho"));
                lote.setNumeroLote(rs.getInt("numero_lote"));
                lote.setIdMorador(rs.getInt("id_morador"));
                lotes.add(lote);
            }
            System.out.println("Lotes listados com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lotes;
    }

    // Método para atualizar um lote
    public void atualizar(Lote lote) {
        String sql = "UPDATE Lote SET tamanho = ?, numero_lote = ?, id_morador = ? WHERE id_lote = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, lote.getTamanho());
            stmt.setInt(2, lote.getNumeroLote());
            stmt.setInt(3, lote.getIdMorador());
            stmt.setInt(4, lote.getIdLote());
            stmt.executeUpdate();
            System.out.println("Lote atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um lote
    public void excluir(int id) {
        String sql = "DELETE FROM Lote WHERE id_lote = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Lote excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
