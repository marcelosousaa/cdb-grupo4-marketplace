package br.com.cdb.java.grupo4.marketplace.service;

import java.util.List;

import br.com.cdb.java.grupo4.marketplace.model.Usuario;

public class UsuarioService {

    public static void listarUsuarios(List<Usuario> listaDeUsuarios) {

        if (!listaDeUsuarios.isEmpty()) {

            System.out.println("\tLista de Usuarios\n"
                    + "Id"
                    + "\tNome"
                    + "\tFuncao");

            for (Usuario usuario : listaDeUsuarios) {
                System.out.println((listaDeUsuarios.indexOf(usuario)) + " \t" + usuario.getNome() + " \t" + usuario.getFuncao());
            }
        } else {
            System.out.println("A lista esta vazia!");
        }
    }
}
