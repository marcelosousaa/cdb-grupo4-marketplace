package br.com.cdb.java.grupo4.marketplace.model;

public abstract class Usuario {
    private long id;
    private String nome;
    private String senha;
    private String email;
    private char funcao;

    public Usuario(long id, String nome, String senha, String email, char funcao) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.funcao = funcao;
    }

    public long getId(){
        return this.id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public char getFuncao() {
        return funcao;
    }
}
