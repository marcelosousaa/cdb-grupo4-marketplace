package br.com.cdb.java.grupo4.marketplace.service;

import java.io.Console;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.cdb.java.grupo4.marketplace.model.Administrador;
import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;
import br.com.cdb.java.grupo4.marketplace.util.ValidatorUtil;

public class LoginService {

    public static int telaDeLogin() {
        int opcaoInicial = 0;
        while (true) {
            System.out.println("\n######### Bem vindo ao nosso Marketplace #########\n");
            System.out.println("\nSelecione uma opcao abaixo:\n "
                    + "\n1 - Acesso ao Menu"
                    + "\n2 - Cadastrar-se (Cliente)"
                    + "\n0 - Sair");
            try {
                opcaoInicial = new Scanner(System.in).nextInt();

                if (opcaoInicial < 0 || opcaoInicial > 2) {
                    System.out.println("Opcao invalida!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Caracter invalido!");
            }
        }
        return opcaoInicial;
    }

    public static Usuario login(List<Usuario> listaDeUsuarios)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        ValidatorUtil validatorUtil = new ValidatorUtil();
        Usuario usuario = null;
        String email = null;
        char[] senhaChar;
        String senhaString = null;
        Console console = null;

        while (usuario == null) {

            while (true) {
                System.out.println("Digite seu email: ");
                email = new Scanner(System.in).nextLine();
                if (email.isEmpty()) {
                    System.err.println("Campo obrigatorio!");
                } else if (!validatorUtil.validaEmail(email)) {
                    System.err.println("Formato invalido!");
                } else {
                    break;
                }
            }

            while (true) {
                console = System.console();
                senhaChar = console.readPassword("Digite sua senha: ");

                if (senhaChar.length == 0) {
                    System.err.println("Campo obrigatorio!");
                } else {
                    senhaString = new String(senhaChar);
                    break;
                }
            }

            usuario = LoginService.validaLogin(listaDeUsuarios, email, senhaString);

            if (usuario != null) {
                System.out.println("\nLogin realizado com sucesso!\n" + "Bem-vindo " + usuario.getNome() + "!");
            }
        }

        return usuario;

    }

    private static Usuario validaLogin(List<Usuario> listaDeUsuarios, String email, String senha)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        Usuario usuario = null;
        if (!listaDeUsuarios.isEmpty()) {
            for (int i = 0; i < listaDeUsuarios.size(); i++) {
                if (listaDeUsuarios.get(i).getEmail().equals(email)) {
                    if (listaDeUsuarios.get(i).getFuncao() == 'A') {
                        if (listaDeUsuarios.get(i).getSenha().equals(senha)) {
                            usuario = new Administrador();
                            usuario.setId(listaDeUsuarios.get(i).getId());
                            usuario.setNome(listaDeUsuarios.get(i).getNome());
                            usuario.setEmail(listaDeUsuarios.get(i).getEmail());
                            usuario.setFuncao('A');
                            break;
                        } else {
                            System.out.println(
                                    "\nNao foi possivel realizar o login, verifique seus dados e tente novamente.\n");
                            usuario = null;
                        }
                    } else if (PasswordService.validarSenha(senha, listaDeUsuarios.get(i).getSenha()) == true) {
                        usuario = new Cliente();
                        usuario.setId(listaDeUsuarios.get(i).getId());
                        usuario.setNome(listaDeUsuarios.get(i).getNome());
                        usuario.setEmail(listaDeUsuarios.get(i).getEmail());
                        usuario.setFuncao('C');
                        ((Cliente) usuario).setTelefone(((Cliente) listaDeUsuarios.get(i)).getTelefone());
                        ((Cliente) usuario).setEndereco(((Cliente) listaDeUsuarios.get(i)).getEndereco());
                        ((Cliente) usuario)
                                .setDataDeNascimento(((Cliente) listaDeUsuarios.get(i)).getDataDeNascimento());
                        ((Cliente) usuario).setSaldo(((Cliente) listaDeUsuarios.get(i)).getSaldo());
                        break;
                    } else {
                        System.out.println(
                                "\nNao foi possivel realizar o login, verifique seus dados e tente novamente.\n");
                        usuario = null;
                    }
                } else if (i < listaDeUsuarios.size() - 1) {
                    System.out.println("\nBuscando...\n");
                } else {
                    System.out
                            .println("\nNao foi possivel realizar o login, verifique seus dados e tente novamente.\n");
                    usuario = null;
                }
            }
        } else {
            System.out.println("Nao ha usuarios cadastrados!");
            usuario = null;
        }
        return usuario;
    }

}