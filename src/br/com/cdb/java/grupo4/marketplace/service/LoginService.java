package br.com.cdb.java.grupo4.marketplace.service;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import br.com.cdb.java.grupo4.marketplace.model.Administrador;
import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;

public class LoginService {

    public static int telaDeLogin() {
        int opcaoInicial = 0;
        while (true) {
            System.out.println("\n######### Bem vindo ao nosso Marketplace #########");
            System.out.println("\nSelecione uma opcao abaixo:\n "
                    + "\n1 - Acesso ao menu principal"
                    + "\n2 - Cadastro"
                    + "\n0 - Sair");
            try {
                opcaoInicial = new Scanner(System.in).nextInt();

                if (opcaoInicial < 1 || opcaoInicial > 2) {
                    System.out.println("Opcao invalida!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Caracter invalido!");
            }
        }
        return opcaoInicial;
    }

    public static Usuario login(List<Usuario> listaDeUsuarios) {
        String email = null;
        char[] senhaChar;
        String senhaString = null;
        Usuario usuario = null;
        Console console = null;

        while (usuario == null) {
            System.out.print("Digite seu email: ");
            email = new Scanner(System.in).next();
            if (email.isEmpty()) {
                System.err.println("Campo obrigatorio!");
                usuario = null;
            } else {
                console = System.console();
                senhaChar = console.readPassword("Digite sua senha: ");

                if (senhaChar.length == 0) {
                    System.err.println("Campo obrigatorio!");
                    usuario = null;
                } else {
                    senhaString = new String(senhaChar);
                }
            }
            usuario = LoginService.validaLogin(listaDeUsuarios, email, senhaString);
        }
        return usuario;
    }

    private static Usuario validaLogin(List<Usuario> listaDeUsuarios, String email, String senha) {
        Usuario usuario = null;
        if (!listaDeUsuarios.isEmpty()) {
            for (int i = 0; i < listaDeUsuarios.size(); i++) {
                if (listaDeUsuarios.get(i).getEmail().equals(email)) {
                    if (listaDeUsuarios.get(i).getSenha().equals(senha)) {
                        if (listaDeUsuarios.get(i).getFuncao() == 'C') {
                            usuario = new Cliente(
                                    listaDeUsuarios.get(i).getId(),
                                    listaDeUsuarios.get(i).getNome(),
                                    listaDeUsuarios.get(i).getSenha(),
                                    listaDeUsuarios.get(i).getEmail(),
                                    ((Cliente) listaDeUsuarios.get(i)).getTelefone(),
                                    ((Cliente) listaDeUsuarios.get(i)).getEndereco(),
                                    ((Cliente) listaDeUsuarios.get(i)).getDataDeNascimento());
                        } else {
                            usuario = new Administrador(
                                    listaDeUsuarios.get(i).getId(),
                                    listaDeUsuarios.get(i).getNome(),
                                    listaDeUsuarios.get(i).getSenha(),
                                    listaDeUsuarios.get(i).getEmail());
                        }
                        System.out.println("\nLogin realizado com sucesso!\n");
                    } else {
                        System.out.println(
                                "\nNao foi possivel realizar o login, verifique seus dados e tente novamente.\n");
                    }
                } else {
                    System.out
                            .println("\nNao foi possivel realizar o login, verifique seus dados e tente novamente.\n");
                }
            }
        } else {
            System.out.println("Nao ha usuarios cadastrados!");
        }
        return usuario;
    }
}