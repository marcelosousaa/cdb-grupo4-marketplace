package br.com.cdb.java.grupo4.marketplace.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Produto;

public class ProdutoService {

    public static void listarProdutos(List<Produto> listaDeProdutos) {
        if (!listaDeProdutos.isEmpty()) {
            System.err.println("ID" + "\tNome" + "\t\tQuantidade" + "\tPreco");
            for (Produto produto : listaDeProdutos) {
                System.out.println(produto.toString());
            }
        } else {
            System.err.println("A lista esta vazia!\n");
        }
    }

    public static Produto cadastrarProduto(List<Produto> listaDeProdutos) {
        String nome = null;
        String descricao = null;
        double preco = 0d;
        int quantidade = 0;
        Produto produto = null;
        long totalDeProdutos = 0l;
        long idProduto = 0l;

        totalDeProdutos = listaDeProdutos.size();
        idProduto = totalDeProdutos + 1;

        while (true) {
            System.out.println("Digite o nome do produto:");
            nome = new Scanner(System.in).nextLine();
            if (nome.isEmpty()) {
                System.err.println("Nome obrigatorio!");
            } else {
                break;
            }
        }

        // DESCRICAO E OPCIONAL
        System.out.println("Digite a descricao do produto:");
        descricao = new Scanner(System.in).nextLine();

        while (true) {
            try {
                System.out.println("Digite a quantidade:");
                quantidade = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Caracter invalido!");
            }
        }

        while (true) {
            try {
                System.out.println("Digite o preco do produto:");
                preco = new Scanner(System.in).nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Caracter invalido!");
            }
        }

        try {
            produto = new Produto(idProduto, nome, descricao, preco, quantidade);
            System.out.println("\nProduto cadastrado com sucesso!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }

    public static void atualizarEstoque(List<Produto> listaDeProdutos, Produto produto, int quantidade) {
        if (!listaDeProdutos.isEmpty()) {
            for (Produto p : listaDeProdutos) {
                if (p.getNome().equals(produto.getNome())) {
                    int quantidadeAtual;
                    quantidadeAtual = produto.getQuantidade();
                    produto.setQuantidade(quantidadeAtual - quantidade);
                }
            }
        }
    }
}
