import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Administrador;
import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Produto;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;
import br.com.cdb.java.grupo4.marketplace.service.AdmService;
import br.com.cdb.java.grupo4.marketplace.service.ClienteService;
import br.com.cdb.java.grupo4.marketplace.service.LoginService;
import br.com.cdb.java.grupo4.marketplace.service.ProdutoService;
//import br.com.cdb.java.grupo4.marketplace.service.UsuarioService;

public class Main {
    public static void main(String[] args) throws Exception {

        // DECLARACAO DE VARIAVEIS
        int ad = 0;
        boolean rodandoMain = false;
        Usuario usuario = null;
        Cliente cliente = null;
        Administrador adm = null;
        List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

        // CRIAR ADM PADRAO
        adm = AdmService.criaAdministrador();
        listaDeUsuarios.add(adm);
        // UsuarioService.listarUsuarios(listaDeUsuarios);

        // INICIO DA APLICACAO
        int opcaoRetornada = LoginService.telaDeLogin();

        while (rodandoMain == false) {
            switch (opcaoRetornada) {
                case 1:
                    usuario = LoginService.login(listaDeUsuarios);
                    menuPrincipal(usuario);
                    rodandoMain = true;
                    break;
                case 2:
                    cliente = ClienteService.cadastrarCliente();
                    listaDeUsuarios.add(cliente);
                    // UsuarioService.listarUsuarios(listaDeUsuarios);
                    opcaoRetornada = LoginService.telaDeLogin();
                    rodandoMain = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuPrincipal(Usuario usuario) {
        boolean rodandoMenuPrincipal = false;
        int opcaoSelecionada = 0;

        List<Produto> listaDeProdutos = new ArrayList<Produto>();

        while (rodandoMenuPrincipal == false) {
            if (usuario.getFuncao() == 'A') {
                System.out.println("\n######## Gerenciamento de estoque ########");
                System.out.println("Selecione uma opcao no menu abaixo: \n"
                        + "\n 1 - Listar estoque"
                        + "\n 2 - Cadastrar novo produto"
                        + "\n 3 - Registar recebimento de produtos"
                        + "\n 0 - sair");
                try {
                    opcaoSelecionada = new Scanner(System.in).nextInt();
                    if (opcaoSelecionada < 0 || opcaoSelecionada > 3) {
                        System.out.println("Opcao invalida!");
                    } else if (opcaoSelecionada == 1) {
                        ProdutoService.listarProdutos(listaDeProdutos);
                        System.out.println("Pressione qualquer tecla para retornar ao menu");
                        new Scanner(System.in).nextLine();
                        rodandoMenuPrincipal = false;
                    } else if (opcaoSelecionada == 2) {
                        Produto novoProduto = null;
                        novoProduto = ProdutoService.adicionarProduto();
                        listaDeProdutos.add(novoProduto);
                        System.out.println("Pressione qualquer tecla para retornar ao menu");
                        new Scanner(System.in).nextLine();
                        rodandoMenuPrincipal = false;
                    } else if (opcaoSelecionada == 3) {
                        if (!listaDeProdutos.isEmpty()) {
                            ProdutoService.listarProdutos(listaDeProdutos);
                        } else {
                            System.err.println("Ainda nao ha produtos cadastrados...");
                            System.out.println("Pressione qualquer tecla para retornar ao menu");
                            new Scanner(System.in).nextLine();
                            rodandoMenuPrincipal = false;
                        }
                    } else {
                        System.out.println("\nEncerrando...");
                        System.out.println("Obrigado por utilizar o nosso sistema!\n");
                        rodandoMenuPrincipal = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Caracter invalido!");
                    rodandoMenuPrincipal = false;
                }
            } else {
                System.out.println("\nSelecione uma opcao no menu abaixo:"
                        + "\n 1 - Gerenciar minha carteira"
                        + "\n 2 - Compras");
                try {
                    opcaoSelecionada = new Scanner(System.in).nextInt();
                    if (opcaoSelecionada < 0 || opcaoSelecionada > 2) {
                        System.out.println("Opcao invalida!");
                    } else if (opcaoSelecionada == 1) {
                        ClienteService.gerenciarCarteira(usuario);
                        rodandoMenuPrincipal = true;
                    } else {
                        ClienteService.menuCompras(usuario);
                        rodandoMenuPrincipal = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Caracter invalido!");
                }
            }
        }

    }

}
