package br.com.cdb.java.grupo4.marketplace.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Produto;
import br.com.cdb.java.grupo4.marketplace.util.ValidatorUtil;

public class ProdutoService {

    public static void listarProdutos(List<Produto> listaDeProdutos) {
        if (!listaDeProdutos.isEmpty()) {

            int larguraColunaId = 10;
            int larguraColunaNome = 20;
            int larguraColunaDescricao = 30;
            int larguraColunaPreco = 10;
            int larguraColunaQuantidade = 20;

            System.out.println(String.format("%-"
                    + larguraColunaId + "s%-"
                    + larguraColunaNome + "s%-"
                    + larguraColunaDescricao + "s%-"
                    + larguraColunaQuantidade + "s%-"
                    + larguraColunaPreco + "s",
                    "ID", "Nome", "Descricao", "Quantidade", "Preco"));

            System.out.println("-".repeat(larguraColunaId
                    + larguraColunaNome
                    + larguraColunaDescricao
                    + larguraColunaQuantidade
                    + larguraColunaPreco));

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

    public static List<Produto> cadastrarProduto(
            List<Produto> listaDeProdutos, String nome, String descricao, double preco, int quantidade) {

        long totalDeProdutos = 0l;
        long idProduto = 0l;
        Produto produto = null;

        totalDeProdutos = listaDeProdutos.size();
        idProduto = totalDeProdutos + 1;

        try {
            produto = new Produto(idProduto, nome, descricao, preco, quantidade);
            listaDeProdutos.add(produto);
            System.out.println("\nProduto cadastrado com sucesso!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeProdutos;
    }

    public static void venderProduto(List<Produto> listaDeProdutos, long idDoProduto, int quantidadeComprada) {
        int quantidadeAtual = 0;

        if (!listaDeProdutos.isEmpty()) {
            for (int i = 0; i < listaDeProdutos.size(); i++) {
                if (listaDeProdutos.get(i).getId() == idDoProduto) {
                    quantidadeAtual = listaDeProdutos.get(i).getQuantidade();

                    if (quantidadeAtual > quantidadeComprada) {
                        listaDeProdutos.get(i).subtrairQuantidade(quantidadeComprada);
                    } else {
                        System.err.println("Quantidade indisponivel!");
                    }

                } else if (i < listaDeProdutos.size()) {
                    System.out.println("Buscando...");
                } else {
                    System.out.println("Produto nao localizado!");
                }
            }
        } else {
            System.err.println("Houve um erro, por favor contat o Administrador do sistema.");
        }
    }

    public static List<Produto> importarProdutosDoArquivo(List<Produto> listaDeProdutos)
            throws IOException, InterruptedIOException {

        ValidatorUtil validatorUtil = new ValidatorUtil();
        
        while (true) {
            System.out.println("Digite o nome do arquivo:");
            String nomeArquivo = new Scanner(System.in).nextLine();
            if (!nomeArquivo.isEmpty()) {
                if (!validatorUtil.validarNomeDoArquivoDeImportacao(nomeArquivo)) {
                    System.err.println("Formato invalido!");
                } else {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader(nomeArquivo));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] campos = line.split(",");
                            String nome = campos[0];
                            String descricao = campos[1];
                            double preco = Double.parseDouble(campos[2]);
                            int quantidade = Integer.parseInt(campos[3]);
                            listaDeProdutos = ProdutoService.cadastrarProduto(
                                    listaDeProdutos,
                                    nome,
                                    descricao,
                                    preco,
                                    quantidade);
                        }
                        reader.close();
                    } catch (IOException e) {
                        System.err.println("Erro ao carregar o arquivo: " + nomeArquivo);
                    }
                    ProdutoService.listarProdutos(listaDeProdutos);
                    validatorUtil = null;
                    break;
                }
    
            } else {
                System.err.println("Digite o nome do arquivo!");
            }   
        }
        return listaDeProdutos;
    }

    public static List<Produto> gerenciarEstoque(List<Produto> listaDeProdutos) {
        if (!listaDeProdutos.isEmpty()) {
            System.out.println("\nDigite o ID do produto que deseja atualizar: ");
            try {
                long idProduto = new Scanner(System.in).nextLong();

                for (int i = 0; i < listaDeProdutos.size(); i++) {
                    if (listaDeProdutos.get(i).getId() == idProduto) {
                        System.out.println("Produto localizado!");

                        while (true) {
                            System.out.println("Selecione o tipo de atualizacao\n"
                                    + "1 - Atualizar preco\n"
                                    + "2 - Atualizar quantidades");

                            try {
                                int opcaoSelecionada = new Scanner(System.in).nextInt();
                                if (opcaoSelecionada < 0 || opcaoSelecionada > 2) {
                                    System.out.println("Opcao invalida!");
                                } else {
                                    switch (opcaoSelecionada) {
                                        case 1:
                                            listaDeProdutos = atualizarPreco(listaDeProdutos, idProduto);
                                            listarProdutos(listaDeProdutos);
                                            break;
                                        case 2:
                                            listaDeProdutos = atualizarQuantidade(listaDeProdutos, idProduto);
                                            listarProdutos(listaDeProdutos);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Caracter invalido!");
                            }
                            break;
                        }
                        break;
                    } else if (i < listaDeProdutos.size() - 1) {
                        System.out.println("Buscando...");
                    } else {
                        System.err.println("Produto nao localizado.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Caracter invalido!");
            }
        } else {
            System.out.println("A lista de produtos esta vazia!");
        }

        return listaDeProdutos;
    }

    private static List<Produto> atualizarQuantidade(List<Produto> listaDeProdutos, long idProduto) {
        for (int i = 0; i < listaDeProdutos.size(); i++) {
            if (listaDeProdutos.get(i).getId() == idProduto) {
                while (true) {
                    System.out.println("\nDigite a quantidade que deseja adicionar: ");
                    try {
                        int quantidadeAdicional = new Scanner(System.in).nextInt();
                        if (quantidadeAdicional < 0) {
                            System.out.println("Valor invalido!");
                        } else {
                            listaDeProdutos.get(i).adicionarQuantidade(quantidadeAdicional);
                            System.out.println("Quantidade atualizado!\n");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Caracter e/ou formato invalido!");
                    }
                }
            } 
        }
        return listaDeProdutos;
    }

    private static List<Produto> atualizarPreco(List<Produto> listaDeProdutos, long idProduto) {
        for (int i = 0; i < listaDeProdutos.size(); i++) {
            if (listaDeProdutos.get(i).getId() == idProduto) {
                while (true) {
                    System.out.println("Digite o novo preco: ");
                    try {
                        double novoPreco = new Scanner(System.in).nextDouble();
                        if (novoPreco < 0) {
                            System.out.println("Valor invalido!");
                        } else {
                            listaDeProdutos.get(i).setPreco(novoPreco);
                            System.out.println("Valor atualizado!\n");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Caracter e/ou formato invalido!");
                    }
                }
            }
        }
        return listaDeProdutos;
    }
}
