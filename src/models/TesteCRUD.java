package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TesteCRUD {
    public static void main(String[] args) {
        verificarConexaoEBanco();
        testarPlanta();
        testarLote();
    }

    private static void verificarConexaoEBanco() {
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
            System.out.println("Tabelas no banco de dados:");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    public static void testarPlanta() {
        PlantaDAO plantaDAO = new PlantaDAO();

        // Criar e inserir uma nova planta
        Planta novaPlanta = new Planta();
        novaPlanta.setNome("Tomate");
        novaPlanta.setDataPlantio(java.time.LocalDate.now());
        novaPlanta.setDataColheita(java.time.LocalDate.now().plusDays(90));
        novaPlanta.setIdLote(1);
        plantaDAO.inserir(novaPlanta);

        // Listar todas as plantas
        List<Planta> plantas = plantaDAO.listar();
        System.out.println("Lista de Plantas:");
        for (Planta planta : plantas) {
            System.out.println("ID: " + planta.getIdPlanta() +
                    ", Nome: " + planta.getNome() +
                    ", Data Plantio: " + planta.getDataPlantio() +
                    ", Data Colheita: " + planta.getDataColheita() +
                    ", ID Lote: " + planta.getIdLote());
        }

        // Atualizar uma planta
        if (!plantas.isEmpty()) {
            Planta plantaParaAtualizar = plantas.get(0);
            plantaParaAtualizar.setNome("Tomate Atualizado");
            plantaParaAtualizar.setDataColheita(java.time.LocalDate.now().plusDays(120));
            plantaDAO.atualizar(plantaParaAtualizar);
            System.out.println("Planta atualizada com sucesso!");
        }

        // Excluir uma planta
        if (!plantas.isEmpty()) {
            int idParaExcluir = plantas.get(0).getIdPlanta();
            plantaDAO.excluir(idParaExcluir);
            System.out.println("Planta excluída com sucesso!");
        }
    }

    public static void testarLote() {
        LoteDAO loteDAO = new LoteDAO();

        // Criar e inserir um novo lote
        Lote novoLote = new Lote();
        novoLote.setTamanho(200.5);
        novoLote.setNumeroLote(5);
        novoLote.setIdMorador(1);
        loteDAO.inserir(novoLote);

        // Listar todos os lotes
        List<Lote> lotes = loteDAO.listar();
        System.out.println("Lista de Lotes:");
        for (Lote lote : lotes) {
            System.out.println("ID: " + lote.getIdLote() +
                    ", Tamanho: " + lote.getTamanho() +
                    ", Número do Lote: " + lote.getNumeroLote() +
                    ", ID Morador: " + lote.getIdMorador());
        }

        // Atualizar um lote
        if (!lotes.isEmpty()) {
            Lote loteParaAtualizar = lotes.get(0);
            loteParaAtualizar.setTamanho(300.0);
            loteParaAtualizar.setNumeroLote(10);
            loteDAO.atualizar(loteParaAtualizar);
            System.out.println("Lote atualizado com sucesso!");
        }

        // Excluir um lote
        if (!lotes.isEmpty()) {
            int idParaExcluir = lotes.get(0).getIdLote();
            loteDAO.excluir(idParaExcluir);
            System.out.println("Lote excluído com sucesso!");
        }
    }
}
