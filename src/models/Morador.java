package models;

public class Morador {
    private int idMorador;
    private String nome;
    private String profissao;
    private String telefone;
    private String endereco;

    // Construtor padrão
    public Morador() {
    }

    // Construtor com todos os argumentos
    public Morador(int idMorador, String nome, String profissao, String telefone, String endereco) {
        this.idMorador = idMorador;
        this.nome = nome;
        this.profissao = profissao;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e setters
    public int getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(int idMorador) {
        this.idMorador = idMorador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
