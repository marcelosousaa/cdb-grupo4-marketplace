package br.com.cdb.java.grupo4.marketplace.model;

public final class Cliente extends Usuario {
    private String dataDeNascimento;
    private double saldo;

    public Cliente(int id, String nome, String senha, String email, String dataDeNascimento) {
        super(id, nome, senha, email);
        this.saldo += 100;
        this.dataDeNascimento = dataDeNascimento;
    }
}
