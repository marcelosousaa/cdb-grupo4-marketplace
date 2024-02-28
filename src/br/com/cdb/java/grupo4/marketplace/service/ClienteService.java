package br.com.cdb.java.grupo4.marketplace.service;

import java.io.Console;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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

    public static void realizarCompra(Cliente cliente, List<Produto> listaDeProdutos, List<Cliente> listaDeUsuarios) {
        Scanner scanner = new Scanner(System.in);

        List<Produto> produtosSelecionados = new ArrayList<>();

        // Mostra os produtos disponíveis para compra
        System.out.println("Produtos disponíveis para compra:");
        ProdutoService.listarProdutos(listaDeProdutos);

        // Loop para adicionar produtos ao carrinho
        while (true) {
            // Solicita o ID do produto que o cliente deseja adicionar ao carrinho
            System.out.print("\nDigite o ID do produto que deseja adicionar ao carrinho (0 para finalizar a compra): ");
            int produtoId = scanner.nextInt();

            if (produtoId == 0) {
                break;
            }

            // Verifica se o produto com o ID fornecido existe na lista de produtos
            Produto produtoSelecionado = null;
            for (Produto produto : listaDeProdutos) {
                if (produto.getId() == produtoId) {
                    produtoSelecionado = produto;
                    break;
                }
            }

            if (produtoSelecionado == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            // Solicita a quantidade do produto a ser adicionada ao carrinho
            System.out.print("Digite a quantidade do produto: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha pendente

            // Verifica se há estoque suficiente para o produto selecionado
            if (quantidade > produtoSelecionado.getQuantidade()) {
                System.out.println("Estoque insuficiente para o produto selecionado.");
                continue;
            }

            // Adiciona o produto à lista de produtos selecionados pelo cliente
            for (int i = 0; i < quantidade; i++) {
                produtosSelecionados.add(produtoSelecionado);
            }

            // Pergunta se o cliente deseja continuar comprando
            System.out.print("Deseja continuar comprando? (S/N): ");
            String continuar = scanner.next();
            if (!continuar.equalsIgnoreCase("S")) {
                break;
            }
        }

        // Exibir os produtos selecionados e o valor total da compra
        double valorTotal = 0;
        System.out.println("Produtos selecionados:");
        Set<Produto> produtosUnicos = new HashSet<>(produtosSelecionados);
        for (Produto produto : produtosUnicos) {
            
            int quantidadeTotal = Collections.frequency(produtosSelecionados, produto);
    
            double precoTotal = quantidadeTotal * produto.getPreco();
            System.out.println("- " + produto.getDescricao() + " - Quantidade: "
            + quantidadeTotal + " - Valor Total: R$" + precoTotal);
    
            valorTotal += precoTotal;
        }
        System.out.println("Valor total da compra: R$" + valorTotal);

        // Confirmar a compra
        System.out.print("Deseja confirmar a compra? (S/N): ");
        String confirmacao = scanner.next();
        if (confirmacao.equalsIgnoreCase("S")) {
            // Verificar se o cliente tem saldo suficiente
            if (cliente.getSaldo() < valorTotal) {
                System.out.println("Saldo insuficiente. A compra não pôde ser realizada.");
                return;
            }

            // Atualizar o saldo do cliente
            cliente.setSaldo(cliente.getSaldo() - valorTotal);

            // Atualizar a quantidade de produtos após a compra
            for (Produto produto : produtosSelecionados) {
                produto.subtrairQuantidade(1);
            }

            // Atualizar o saldo do cliente na lista de usuários
            for (Cliente usuario : listaDeUsuarios) {
                if (usuario.getId() == cliente.getId()) {
                    usuario.setSaldo(cliente.getSaldo());
                    break; // Não é necessário continuar procurando
                }
            }

            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Compra cancelada.");
        }
    }

}
