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

        //DECLARACAO DE VARIAVEIS
        Usuario usuario = null;
        Cliente cliente = null;
        Administrador adm = null;
        List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

        //CRIAR ADM PADRAO
        adm = AdmService.criaAdministrador();
        listaDeUsuarios.add(adm);
        //UsuarioService.listarUsuarios(listaDeUsuarios);

        // INICIO DA APLICACAO
        int opcaoRetornada = LoginService.telaDeLogin();

        switch (opcaoRetornada) {
            case 1:
                usuario = LoginService.login(listaDeUsuarios);
                menuPrincipal(usuario);
                break;
            case 2:
                cliente = ClienteService.cadastrarCliente();
                listaDeUsuarios.add(cliente);
                //UsuarioService.listarUsuarios(listaDeUsuarios);
                opcaoRetornada = LoginService.telaDeLogin();
            default:
                break;
        }
    }

    public static void menuPrincipal(Usuario usuario) {
        int opcaoSelecionada = 0;

        List<Produto> listaDeProdutos = new ArrayList<Produto>();

        if (usuario.getFuncao() == 'A') {
            while (true) {

                System.out.println("######## Gerenciamento de estoque ########");
                System.out.println("Selecione uma opcao no menu abaixo: "
                        + " 1 - Listar estoque"
                        + " 2 - Cadastrar novo produto"
                        + " 3 - Registar recebimento de produtos");
                try {
                    opcaoSelecionada = new Scanner(System.in).nextInt();
                    if (opcaoSelecionada < 0 || opcaoSelecionada > 3) {
                        System.out.println("Opcao invalida!");
                    } else if (opcaoSelecionada == 1) {
                        ProdutoService.listarProdutos(listaDeProdutos);
                        break;
                    } else if (opcaoSelecionada == 2) {
                        Produto novoProduto = null;
                        novoProduto = ProdutoService.adicionarProduto();
                        listaDeProdutos.add(novoProduto);
                        break;
                    } else {

                        //ProdutoService.atualizarEstoque(listaDeProdutos, produto, quantidade);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Caracter invalido!");
                }
            }
        } else {
            while (true) {
                System.out.println("Selecione uma opcao no menu abaixo: "
                        + " 1 - Gerenciar minha carteira"
                        + " 2 - Compras");
                try {
                    opcaoSelecionada = new Scanner(System.in).nextInt();
                    if (opcaoSelecionada < 0 || opcaoSelecionada > 2) {
                        System.out.println("Opcao invalida!");
                    } else if (opcaoSelecionada == 1) {
                        ClienteService.gerenciarCarteira(usuario);
                        break;
                    } else {
                        ClienteService.menuCompras(usuario);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Caracter invalido!");
                }
            }
        }
    }
}
