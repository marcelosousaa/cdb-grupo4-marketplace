package br.com.cdb.java.grupo4.marketplace.service;

import br.com.cdb.java.grupo4.marketplace.model.Administrador;

public class AdmService {

    public static Administrador criarAdministrador() {
        Administrador adm = new Administrador(1, "admin", "admin", "admin@teste.com");
        return adm;
    }
}
