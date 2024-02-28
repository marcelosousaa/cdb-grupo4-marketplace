import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Administrador;
import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Produto;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;
import br.com.cdb.java.grupo4.marketplace.service.AdmService;
import br.com.cdb.java.grupo4.marketplace.service.CarteiraService;
import br.com.cdb.java.grupo4.marketplace.service.ClienteService;
import br.com.cdb.java.grupo4.marketplace.service.LoginService;
import br.com.cdb.java.grupo4.marketplace.service.ProdutoService;

public class Main {
    public static void main(String[] args) throws Exception {

        // CONTROLE DA EXECUCAO DA APLICACAO
        boolean rodandoAplicacao = false;
        boolean rodandoMenuLogin = false;
        boolean rodandoMenuPrincipal = false;

        // DECLARACAO DE VARIAVEIS
        Usuario usuario = null;
        Cliente cliente = null;
        Administrador adm = null;
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        List<Produto> listaDeProdutos = new ArrayList<>();
        List<Cliente> listaDeClientes = new ArrayList<>(); // Adicione esta lista

        // CRIAR ADM PADRAO
        adm = AdmService.criarAdministrador();
        listaDeUsuarios.add(adm);
        adm = null; // LIBERA A VARIAVEL

        // INICIO DA APLICACAO
        while (!rodandoAplicacao) {
            int opcaoRetornada = 0;
            opcaoRetornada = LoginService.telaDeLogin();
            rodandoMenuLogin = false;
            rodandoMenuPrincipal = false;

            // MENU LOGIN
            while (!rodandoMenuLogin) {
                switch (opcaoRetornada) {
                    case 1:
                        usuario = LoginService.login(listaDeUsuarios);
                        rodandoMenuLogin = true;
                        break;
                    case 2:
                        cliente = ClienteService.cadastrarCliente(listaDeUsuarios);
                        if (cliente == null) {
                            opcaoRetornada = LoginService.telaDeLogin();
                        } else {
                            listaDeUsuarios.add(cliente);
                            listaDeClientes.add(cliente); // Adicione o cliente à lista de clientes
                            opcaoRetornada = LoginService.telaDeLogin();
                        }

                        break;
                    case 0:
                        System.out.println("\nEncerrando...");
                        System.out.println("Obrigado por utilizar o nosso sistema!\n");
                        rodandoMenuLogin = true;
                        rodandoMenuPrincipal = true;
                        rodandoAplicacao = true;
                        break;
                    default:
                        break;
                }
            }

            // MENU PRINCIPAL
            int opcaoSelecionada = 0;

            while (rodandoMenuPrincipal == false) {
                if (usuario.getFuncao() == 'A') { // SE FOR ADMINISTRADOR
                    System.out.println("\n######## Gerenciamento de estoque ########");
                    System.out.println("Selecione uma opcao no menu abaixo: \n"
                            + "\n 1 - Listar estoque"
                            + "\n 2 - Cadastrar novo produto"
                            + "\n 3 - Gerenciar produtos no estoque"
                            + "\n 0 - sair");
                    try {
                        opcaoSelecionada = new Scanner(System.in).nextInt();
                        if (opcaoSelecionada < 0 || opcaoSelecionada > 5) {
                            System.out.println("Opcao invalida!");
                            rodandoMenuPrincipal = false;
                        } else {
                            switch (opcaoSelecionada) {
                                case 1:
                                    ProdutoService.listarProdutos(listaDeProdutos);
                                    rodandoMenuPrincipal = false;
                                    break;
                                case 2:
                                    boolean loopMenuCadastroDeProdutos = false;
                                    while (!loopMenuCadastroDeProdutos) {
                                        System.out.println("Deseja cadastrar manualmente ou importar do arquivo(CSV)?\n"
                                                + "1 - Cadastrar manualmente\n"
                                                + "2 - Importar do arquivo\n"
                                                + "0 - Voltar");
                                        try {
                                            int opcaoCadastro = new Scanner(System.in).nextInt();
                                            if (opcaoCadastro < 0 || opcaoCadastro > 2) {
                                                System.err.println("Opcao invalida!");
                                                loopMenuCadastroDeProdutos = false;
                                            } else if (opcaoCadastro == 1) {
                                                Produto novoProduto = null;
                                                novoProduto = ProdutoService.cadastrarProduto(listaDeProdutos);
                                                listaDeProdutos.add(novoProduto);
                                                loopMenuCadastroDeProdutos = true;
                                                rodandoMenuPrincipal = false;
                                            } else if (opcaoCadastro == 2) {
                                                listaDeProdutos = ProdutoService
                                                        .importarProdutosDoArquivo(listaDeProdutos);
                                                loopMenuCadastroDeProdutos = true;
                                                rodandoMenuPrincipal = false;
                                            } else {
                                                System.out.println("Voltando...");
                                                loopMenuCadastroDeProdutos = true;
                                                rodandoMenuPrincipal = false;
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Caracter invalido!");
                                        }
                                    }
                                    break;
                                case 3:
                                    if (!listaDeProdutos.isEmpty()) {
                                        ProdutoService.listarProdutos(listaDeProdutos);
                                        listaDeProdutos = ProdutoService.gerenciarEstoque(listaDeProdutos);
                                        rodandoMenuPrincipal = false;
                                    } else {
                                        System.err.println("Ainda nao ha produtos cadastrados...");
                                        rodandoMenuPrincipal = false;
                                    }
                                    break;
                                case 0:
                                    System.out.println("Retornando ao menu principal...\n");
                                    rodandoMenuPrincipal = true;
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Caracter invalido!");
                        rodandoMenuPrincipal = false;
                    }
                } else { // SE FOR CLIENTE
                    cliente = (Cliente) usuario;

                    System.out.println("\nSelecione uma opcao no menu abaixo:"
                            + "\n 1 - Gerenciar minha carteira"
                            + "\n 2 - Compras"
                            + "\n 0 - Sair");
                    try {
                        opcaoSelecionada = new Scanner(System.in).nextInt();
                        if (opcaoSelecionada < 0 || opcaoSelecionada > 2) {
                            System.out.println("Opcao invalida!");
                        } else {
                            switch (opcaoSelecionada) {
                                case 1:
                                    CarteiraService.gerenciarCarteira(cliente, listaDeUsuarios);
                                    break;
                                case 2:
                                    ClienteService.realizarCompra(cliente, listaDeProdutos, listaDeClientes);
                                    break;
                                case 0:
                                    System.out.println("Retornando ao menu principal...\n");
                                    rodandoMenuPrincipal = true;
                                    break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Caracter invalido!");
                    }
                }
            }
        }
        rodandoAplicacao = true;
    }
}
