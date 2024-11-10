package models;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantaDAO {
    public void inserir(Planta planta) {
        String sql = "INSERT INTO Planta (nome, data_plantio, data_colheita, id_lote) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, planta.getNome());
            pstmt.setString(2, planta.getDataPlantio().toString());
            pstmt.setString(3, planta.getDataColheita().toString());
            pstmt.setInt(4, planta.getIdLote());
            pstmt.executeUpdate();
            System.out.println("Planta inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Planta> listar() {
        List<Planta> plantas = new ArrayList<>();
        String sql = "SELECT * FROM Planta";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idPlanta = rs.getInt("id_planta");
                String nome = rs.getString("nome");
                LocalDate dataPlantio = LocalDate.parse(rs.getString("data_plantio"));
                LocalDate dataColheita = LocalDate.parse(rs.getString("data_colheita"));
                int idLote = rs.getInt("id_lote");

                Planta planta = new Planta(idPlanta, nome, dataPlantio, dataColheita, idLote);
                plantas.add(planta);
            }
            System.out.println("Plantas listadas com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantas;
    }

    public void atualizar(Planta planta) {
        String sql = "UPDATE Planta SET nome = ?, data_plantio = ?, data_colheita = ?, id_lote = ? WHERE id_planta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, planta.getNome());
            pstmt.setString(2, planta.getDataPlantio().toString());
            pstmt.setString(3, planta.getDataColheita().toString());
            pstmt.setInt(4, planta.getIdLote());
            pstmt.setInt(5, planta.getIdPlanta());
            pstmt.executeUpdate();
            System.out.println("Planta atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idPlanta) {
        String sql = "DELETE FROM Planta WHERE id_planta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlanta);
            pstmt.executeUpdate();
            System.out.println("Planta excluída com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
