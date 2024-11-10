package models;

import java.time.LocalDate;

public class Planta {
    private int idPlanta;
    private String nome;
    private LocalDate dataPlantio;
    private LocalDate dataColheita;
    private int idLote;

    // Construtor sem argumentos
    public Planta() {}

    // Construtor com argumentos
    public Planta(int idPlanta, String nome, LocalDate dataPlantio, LocalDate dataColheita, int idLote) {
        this.idPlanta = idPlanta;
        this.nome = nome;
        this.dataPlantio = dataPlantio;
        this.dataColheita = dataColheita;
        this.idLote = idLote;
    }

    // Getters e setters
    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public LocalDate getDataColheita() {
        return dataColheita;
    }

    public void setDataColheita(LocalDate dataColheita) {
        this.dataColheita = dataColheita;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }
}
