package br.com.cdb.java.grupo4.marketplace.model;

public final class Administrador extends Usuario {

    public Administrador(){
    }


    public Administrador(long id, String nome, String senha, String email) {
        super(id ,nome, senha, email, 'A');
    }
}