package br.com.cdb.java.grupo4.marketplace.service;

import java.io.Console;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Produto;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;
import br.com.cdb.java.grupo4.marketplace.util.ValidatorUtil;

public class ClienteService {

    public static Cliente cadastrarCliente(List<Usuario> listaDeUsuarios)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        Console console = null;
        Cliente clienteCadastrado = null;
        String nome;
        String dataDeNascimento = null;
        String senhaString;
        char[] senhaChar;
        String email;
        String telefone;
        String endereco;
        long totalDeClientes = 0l;
        long idCliente = 0l;
        ValidatorUtil validatorUtil = new ValidatorUtil();

        for (Usuario usuario : listaDeUsuarios) {
            if (usuario instanceof Cliente) {
                totalDeClientes++;
            }
        }
        idCliente = totalDeClientes + 1;

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
            System.out.println("Digite sua data de nascimento, no formato (XX/XX/XXXX): ");
            dataDeNascimento = new Scanner(System.in).nextLine();
            if (dataDeNascimento.isEmpty()) {
                System.err.println("Campo obrigatorio!");
            } else if (!validatorUtil.validaDataDeNascimento(dataDeNascimento)) {
                System.err.println("Formato invalido!");
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
                senhaString = PasswordService.gerarSenhaForte(senhaString);
                break;
            }
        }

        while (true) {
            System.out.println("Digite seu email: ");
            email = new Scanner(System.in).nextLine();
            if (email.isEmpty()) {
                System.out.println("Campo obrigatorio!");
            } else if (!validatorUtil.validaEmail(email)) {
                System.err.println("Formato invalido, digite novamente!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Informe seu telefone com DDD, no formato (XX) XXXXX-XXXX: ");
            telefone = new Scanner(System.in).nextLine();
            if (telefone.isEmpty()) {
                System.out.println("Campo obrigatorio!");
            } else if (!validatorUtil.validaTelefone(telefone)) {
                System.err.println("Formato invalido, digite novamente!");
            } else {
                System.out.println(telefone);
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

        clienteCadastrado = new Cliente(idCliente, nome, senhaString, email, telefone, endereco, dataDeNascimento);

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

    public static void exibirCarrinho(List<Produto> carrinho, Cliente cliente) {
        System.out.println("Usuario: " + cliente.getNome());
        System.out.println("Carrinho de compras");
        ProdutoService.listarProdutos(carrinho);
    }

    public static void criarPedido(List<Produto> listaDeProdutos) {
        ProdutoService.listarProdutos(listaDeProdutos);

        long idProduto = 0l;

        System.out.println("Digite o id do produto que deseja comprar: ");

        try {
            idProduto = new Scanner(System.in).nextLong();
            for (int i = 0; i < listaDeProdutos.size(); i++) {

            }

        } catch (InputMismatchException e) {
            System.out.println("Caracter invalido!");
        }
    }

    public static List<Usuario> gerenciarCarteira(Cliente cliente, List<Usuario> listaDeUsuarios) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Gerenciar Carteira:"
                    + "\n[1] - Adicionar saldo"
                    + "\n[2] - Visualizar saldo"
                    + "\n[0] - Voltar");

            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cliente = depositarSaldo(cliente);
                    listaDeUsuarios = atualizaSaldoNoArrayDeUsuario(listaDeUsuarios, cliente);
                    break;
                case 2:
                    double saldo = cliente.getSaldo();
                    System.out.println("Seu saldo atual é: " + saldo);
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
        
        return listaDeUsuarios;
    }

    public static Cliente depositarSaldo(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o valor que deseja adicionar à carteira: ");
        double valor = scanner.nextDouble();

        if (valor > 0) {
            cliente.depositar(valor);
            System.out.println("Saldo depositado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o depósito!");
        }
        return cliente;
    }

    public static List<Usuario> atualizaSaldoNoArrayDeUsuario(List<Usuario> listaUsuarios, Cliente cliente) {
        if (!listaUsuarios.isEmpty()) {
            for (Usuario usuario : listaUsuarios) {
                if (usuario instanceof Cliente) {
                    if (usuario.getId() == cliente.getId()) {
                        ((Cliente) usuario).setSaldo(cliente.getSaldo());
                    } else {
                        System.out.println("Usuario não existe");
                    }
                } else {
                    System.out.println("Não é um cliente.");
                }
            }
        }
        return listaUsuarios;
    }
}
