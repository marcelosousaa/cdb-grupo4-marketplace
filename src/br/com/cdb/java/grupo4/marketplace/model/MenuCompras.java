package br.com.cdb.java.grupo4.marketplace.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.service.ProdutoService;

public class MenuCompras {
    private List<Produto> listaDeProdutos;
    private Cliente cliente;

    public MenuCompras(List<Produto> listaDeProdutos, Cliente cliente) {
        this.listaDeProdutos = listaDeProdutos;
        if (cliente != null) {
            this.cliente = cliente;
        } else {
            throw new IllegalArgumentException("O objeto cliente não pode ser nulo.");
        }
    }

    private void listarProdutosDisponiveis() {
        System.out.println("Lista de Produtos Disponíveis: ");
        ProdutoService.listarProdutos(listaDeProdutos);
        double saldo = cliente.getSaldo();
        System.out.println("Seu saldo atual é: " + saldo);
    }

    public void iniciarCompra() {
        Scanner scanner = new Scanner(System.in);

        List<Produto> carrinho = new ArrayList<>();
        boolean continuarComprando = true;

        while (continuarComprando) {

            listarProdutosDisponiveis();
            Produto produtoEscolhido = escolherProduto();

            if (produtoEscolhido != null) {
                int quantidade = escolherQuantidade(produtoEscolhido);
                produtoEscolhido.setQuantidade(quantidade);
                carrinho.add(produtoEscolhido);
                continuarComprando = continuarComprando();
            } else {
                System.out.println("Produto não encontrado.");
            }
        }
        double precoTotal = calcularPrecoTotal(carrinho);
        boolean compraFinalizada = finalizarCompra(carrinho, precoTotal, cliente);

        if (!compraFinalizada) {
            iniciarCompra();
        }
    }

    private boolean continuarComprando() {
        System.out.println("Deseja escolher outro produto? (S/N):");
        Scanner scanner = new Scanner(System.in);
        char opcao = scanner.next().charAt(0);
        return opcao == 'S' || opcao == 's';
    }

    private boolean finalizarCompra(List<Produto> carrinho, double precoTotal, Cliente cliente) {
        if (cliente == null) {
            System.out.println("Cliente não inicializado. Impossível finalizar compra.");
            return false;
        }

        System.out.println("Itens no Carrinho: ");
        for (Produto produto : carrinho) {
            double precoItem = produto.getPreco() * produto.getQuantidade();
            System.out.println(produto.getDescricao() + " - Quantidade: " + produto.getQuantidade() + " - Preço: " + precoItem);
        }
        System.out.println("Preço Total: " + precoTotal);
    
        return cliente.realizarCompra(precoTotal);
    }

    private Produto escolherProduto() {
        System.out.println("Digite o ID do produto que deseja comprar (0 para sair): ");
        Scanner scanner = new Scanner(System.in);
        long idProduto = scanner.nextLong();
        if (idProduto == 0) {
            return null;
        }

        for (Produto produto : listaDeProdutos) {
            if (produto.getId() == idProduto) {
                return produto;
            }
        }

        return null;
    }

    private int escolherQuantidade(Produto produto) {
        System.out.println("Digite a quantidade desejada para " + produto.getNome() + ":");
        Scanner scanner = new Scanner(System.in);
        int quantidade = scanner.nextInt();

        if (quantidade > produto.getQuantidade()) {
            System.out.println("Quantidade indisponível. Disponível: " + produto.getQuantidade());
            quantidade = produto.getQuantidade();
        }
        return quantidade;
    }

    private double calcularPrecoTotal(List<Produto> carrinho) {
        double precoTotal = 0;
        for (Produto produto : carrinho) {
            double precoItem = produto.getPreco() * produto.getQuantidade();
            precoTotal += precoItem;
        }
        return precoTotal;
    }
}
