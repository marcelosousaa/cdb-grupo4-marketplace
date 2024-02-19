package br.com.cdb.java.grupo4.marketplace.service;

import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Produto;

public class ProdutoService {

    public void listarProdutos(List<Produto> listaDeProdutos) {
        for (Produto produto : listaDeProdutos) {
            System.out.println(produto.toString());
        }
    }

    public Produto adicionarProduto() {
        String nome = null;
        String descricao = null;
        double preco = 0d;
        int quantidade = 0;
        Produto produto = null;

        System.out.println("Digite o nome do produto: ");
        nome = new Scanner(System.in).nextLine();

        System.out.println("Digite a descricao do produto: ");
        descricao = new Scanner(System.in).nextLine();

        System.out.println("Digite a quantidade: ");
        quantidade = new Scanner(System.in).nextInt();

        System.out.println("Digite o preco do produto: ");
        preco = new Scanner(System.in).nextDouble();

        produto = new Produto(nome, descricao, preco, quantidade);

        return produto;
    }

    public void removeProduto(List<Produto> listaDeProdutos, Produto produto) {
        for (Produto p : listaDeProdutos) {
            listaDeProdutos.remove(produto);
        }
    }

    public void atualizarEstoque(List<Produto> listaDeProdutos, Produto produto, int quantidade) {

        for (Produto p : listaDeProdutos) {
            if (p.getNome().equals(produto.getNome())) {
                int quantidadeAtual;
                quantidadeAtual = produto.getQuantidade();
                produto.setQuantidade(quantidadeAtual - quantidade);
            }
        }
    }
}
