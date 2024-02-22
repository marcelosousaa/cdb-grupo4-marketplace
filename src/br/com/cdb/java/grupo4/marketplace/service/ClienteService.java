package br.com.cdb.java.grupo4.marketplace.service;

import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;

public class ClienteService {

    public static Cliente cadastrarCliente() {
        Cliente clienteCadastrado = null;
        String nome;
        String senha;
        String email;
        String telefone;
        String endereco;

        System.out.println("Digite seu nome: ");
        nome = new Scanner(System.in).nextLine();

        System.out.println("Defina uma senha: ");
        senha = new Scanner(System.in).nextLine();

        System.out.println("Digite seu email: ");
        email = new Scanner(System.in).nextLine();

        System.out.println("Digite o seu telefone: ");
        telefone = new Scanner(System.in).nextLine();

        System.out.println("Digite o seu endereço: ");
        endereco = new Scanner(System.in).nextLine();

        clienteCadastrado = new Cliente(nome, senha, email, email, telefone, endereco);

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

    // public static void listarClientes(List<Usuario> listaDeUsuarios) {
    //     if (!listaDeUsuarios.isEmpty()) {
    //         for (Usuario usuario : listaDeUsuarios) {
    //             if (usuario instanceof Cliente) {
    //                 System.out.println(usuario.getId()
    //                         + " - " + usuario.getNome()
    //                         + " - " + usuario.getEmail()
    //                         + " - " + ((Cliente) usuario).getTelefone()
    //                         + " - " + ((Cliente) usuario).getEndereco());
    //             }
    //         }
    //     } else {
    //         System.out.println("Lista de usuarios vazia!");
    //     }
    // }


    public static void menuCompras(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'menuCompras'");
    }

    public static void gerenciarCarteira(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerenciarCarteira'");
    }

}
