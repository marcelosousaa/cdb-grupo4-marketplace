package br.com.cdb.java.grupo4.marketplace.model;

public final class Produto {
    private long id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    
    public Produto(long id, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public long getId() {
        return this.id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return this.id 
        + "  " 
        + this.nome 
        + "\t"
        + this.descricao
        + " \t\t " 
        + this.quantidade 
        + " \t\t R$ " 
        + this.preco;
    }
}
