package br.com.cdb.java.grupo4.marketplace.service;

import br.com.cdb.java.grupo4.marketplace.model.Administrador;

public class AdmService {

    public static Administrador criaAdministrador() {
        Administrador adm = new Administrador("admin", "admin", "admin@teste.com");
        return adm;
    }
}
