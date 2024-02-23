package br.com.cdb.java.grupo4.marketplace.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Produto;

public class ProdutoService {

    public static void listarProdutos(List<Produto> listaDeProdutos) {
        if (!listaDeProdutos.isEmpty()) {
            System.err.println("ID" + "\tNome" + "\t\tQuantidade" + "\tPreco");
            
            
            for (int i=0; i<listaDeProdutos.size(); i++) {
                Produto t = listaDeProdutos.get(i);
                System.out.println("ID " + i);
                System.out.println(t.toString());
                 
        }


        } else {
            System.err.println("A lista esta vazia!\n");
        }
    }

    public static Produto adicionarProduto() {
        String nome = null;
        String descricao = null;
        double preco = 0d;
        int quantidade = 0;
        Produto produto = null;
        boolean loopNome = false;
        boolean loopQuantidade = false;
        boolean loopPreco = false;

        while (!loopNome) {
            System.out.println("Digite o nome do produto:");
            nome = new Scanner(System.in).nextLine();
            if (nome.isEmpty()) {
                System.err.println("Nome obrigatorio!");
                loopNome = false;
            }else{
                loopNome = true;
            }
        }

        // DESCRICAO E OPCIONAL
        System.out.println("Digite a descricao do produto:");
        descricao = new Scanner(System.in).nextLine();

        while (!loopQuantidade) {
            try {
                System.out.println("Digite a quantidade:");
                quantidade = new Scanner(System.in).nextInt();
                loopQuantidade = true;
            } catch (InputMismatchException e) {
                System.out.println("Caracter invalido!");
                loopQuantidade = false;
            }

        }

        while (!loopPreco) {
            try {
                System.out.println("Digite o preco do produto:");
                preco = new Scanner(System.in).nextDouble();
                loopPreco = true;
            } catch (Exception e) {
                System.out.println("Caracter invalido!");
                loopPreco = false;
            }
        }

        produto = new Produto(nome, descricao, preco, quantidade);

        return produto;
    }

    // public void removeProduto(List<Produto> listaDeProdutos, Produto produto) {
    // if (!listaDeProdutos.isEmpty()) {
    // for (Produto p : listaDeProdutos) {
    // if (p.getId() == produto.getId()) {
    // listaDeProdutos.remove(produto);
    // }
    // }
    // } else {
    // System.out.println("A lista esta vazia!");
    // }
    // }

    public static void atualizarEstoque(List<Produto> listaDeProdutos, Produto produto, int quantidade) {
        for (Produto p : listaDeProdutos) {
            if (p.getNome().equals(produto.getNome())) {
                int quantidadeAtual;
                quantidadeAtual = produto.getQuantidade();
                produto.setQuantidade(quantidadeAtual - quantidade);
            }
        }
    }
}
