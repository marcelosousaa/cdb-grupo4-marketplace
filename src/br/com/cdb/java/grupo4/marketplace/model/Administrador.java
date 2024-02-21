package br.com.cdb.java.grupo4.marketplace.model;

public final class Administrador extends Usuario {
    public Administrador(String nome, String senha, String email) {
        super(nome, senha, email, 'A');
    }
}
