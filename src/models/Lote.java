package models;

public class Lote {
    private int idLote;
    private double tamanho;
    private int numeroLote;
    private int idMorador;

    // Construtor sem argumentos
    public Lote() {}

    // Construtor com argumentos
    public Lote(int idLote, double tamanho, int numeroLote, int idMorador) {
        this.idLote = idLote;
        this.tamanho = tamanho;
        this.numeroLote = numeroLote;
        this.idMorador = idMorador;
    }

    // Getters e setters
    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(int numeroLote) {
        this.numeroLote = numeroLote;
    }

    public int getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(int idMorador) {
        this.idMorador = idMorador;
    }
}
