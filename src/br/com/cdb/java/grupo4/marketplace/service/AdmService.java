package br.com.cdb.java.grupo4.marketplace.service;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Administrador;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;

public class AdmService {

    public static Administrador criaAdministrador() {
        Administrador adm = new Administrador(1, "admin", "admin", "admin@teste.com");
        return adm;
    }

    public static Administrador cadastraAdministrador(List<Usuario> listaDeUsuarios){
        String nome = null;
        char [] senhaChar;
        String senhaString = null;
        Console console = null;
        String email = null;
        long totalDeAdministradores = 0l;
        long idAdministrador = 0l;
        
        for (Usuario usuario : listaDeUsuarios) {
            if(usuario instanceof Administrador){
                totalDeAdministradores ++;
            }
        }
        idAdministrador = totalDeAdministradores + 1;

        while (true) {
            System.out.println("Digite seu nome: ");
            nome = new Scanner(System.in).nextLine();
            if (nome.isEmpty()) {
                System.out.println("Campo obrigatorio!");
            } else {
                break;
            }
        }

        while (true) {
            console = System.console();
            senhaChar = console.readPassword("Defina sua senha: ");

            if (senhaChar.length == 0) {
                System.err.println("Campo obrigatorio!");
            } else {
                senhaString = new String(senhaChar);
                break;
            }
        }

        while (true) {
            System.out.println("Digite seu email: ");
            email = new Scanner(System.in).nextLine();
            if (email.isEmpty()) {
                System.out.println("Campo obrigatorio!");
            } else {
                break;
            }
        }

        Administrador novoAdministrador = new Administrador(idAdministrador, nome, senhaString, email);
        return novoAdministrador;
    }
}
