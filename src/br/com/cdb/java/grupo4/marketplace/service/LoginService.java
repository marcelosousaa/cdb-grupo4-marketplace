package br.com.cdb.java.grupo4.marketplace.service;

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
        String senha = null;
        Usuario usuario = null;

        while (usuario == null) {
            System.out.println("Digite seu email: ");
            email = new Scanner(System.in).nextLine();
            if (email.isEmpty()) {
                System.err.println("Campo obrigatorio!");
                usuario = null;
            } else {
                System.out.println("Digite sua senha: ");
                senha = new Scanner(System.in).nextLine();
                if (senha.isEmpty()) {
                    System.err.println("Campo obrigatorio!");
                    usuario = null;
                }
            }
            usuario = LoginService.validaLogin(listaDeUsuarios, email, senha);
        }
        return usuario;
    }

    private static Usuario validaLogin(List<Usuario> listaDeUsuarios, String email, String senha) {
        Usuario usuario = null;
        if (!listaDeUsuarios.isEmpty()) {


            
            for (Usuario u : listaDeUsuarios) {
                if (u.getEmail().equals(email)) {
                    if (u.getSenha().equals(senha)) {
                        if (u.getFuncao() == 'C') {
                            usuario = new Cliente(
                                    u.getNome(),
                                    u.getSenha(),
                                    u.getEmail(),
                                    ((Cliente) u).getTelefone(),
                                    ((Cliente) u).getEndereco(),
                                    ((Cliente) u).getDataDeNascimento());
                        } else {
                            usuario = new Administrador(u.getNome(), u.getSenha(), u.getEmail());
                        }
                        System.out.println("\nLogin realizado com sucesso!\n");
                    } else {
                        System.out.println("Senha inválida!");
                    }
                } else {
                    System.out.println("Usuario nao localizado!");
                }
            }
        } else {
            System.out.println("A lista esta vazia!");
        }
        return usuario;
    }
}
