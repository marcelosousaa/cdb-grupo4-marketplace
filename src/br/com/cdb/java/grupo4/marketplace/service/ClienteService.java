package br.com.cdb.java.grupo4.marketplace.service;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;

public class ClienteService {

    public static Cliente cadastrarCliente() {
        Console console = null;
        Cliente clienteCadastrado = null;
        String nome;
        String senhaString;
        char[] senhaChar;
        String email;
        String telefone;
        String endereco;

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

        while (true) {
            System.out.println("Digite o seu telefone: ");
            telefone = new Scanner(System.in).nextLine();
            if (telefone.isEmpty()) {
                System.out.println("Campo obrigatorio!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Digite o seu endereço: ");
            endereco = new Scanner(System.in).nextLine();
            if (endereco.isEmpty()) {
                System.out.println("Campo obrigatorio!");
            } else {
                break;
            }
        }

        clienteCadastrado = new Cliente(nome, senhaString, email, email, telefone, endereco);

        System.out.println("Cadastro realizado com sucesso!");

        return clienteCadastrado;
    }

    public static void listarClientes(List<Usuario> listaDeUsuarios) {
        if (!listaDeUsuarios.isEmpty()) {
            for (Usuario usuario : listaDeUsuarios) {
                if (usuario instanceof Cliente) {
                    System.out.println(usuario.toString());
                }
            }
        } else {
            System.out.println("Lista de usuarios vazia!");
        }
    }

    public static void menuCompras(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'menuCompras'");
    }

    public static void gerenciarCarteira(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerenciarCarteira'");
    }

}
