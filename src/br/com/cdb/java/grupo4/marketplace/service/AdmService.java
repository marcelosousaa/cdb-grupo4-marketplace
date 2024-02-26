package br.com.cdb.java.grupo4.marketplace.service;

import br.com.cdb.java.grupo4.marketplace.model.Administrador;

public class AdmService {

    public static Administrador criarAdministrador() {
        Administrador adm = new Administrador(1, "admin", "admin", "admin@teste.com");
        return adm;
    }

    // public static Administrador cadastrarAdministrador(List<Usuario> listaDeUsuarios) throws NoSuchAlgorithmException, InvalidKeySpecException{
    //     String nome = null;
    //     char [] senhaChar;
    //     String senhaString = null;
    //     Console console = null;
    //     String email = null;
    //     long totalDeAdministradores = 0l;
    //     long idAdministrador = 0l;
        
    //     for (Usuario usuario : listaDeUsuarios) {
    //         if(usuario instanceof Administrador){
    //             totalDeAdministradores ++;
    //         }
    //     }
    //     idAdministrador = totalDeAdministradores + 1;

    //     while (true) {
    //         System.out.println("Digite o nome: ");
    //         nome = new Scanner(System.in).nextLine();
    //         if (nome.isEmpty()) {
    //             System.out.println("Campo obrigatorio!");
    //         } else {
    //             break;
    //         }
    //     }

    //     while (true) {
    //         console = System.console();
    //         senhaChar = console.readPassword("Defina a senha: ");

    //         if (senhaChar.length == 0) {
    //             System.err.println("Campo obrigatorio!");
    //         } else {
    //             senhaString = new String(senhaChar);
    //             senhaString = PasswordService.gerarSenhaForte(senhaString);
    //             break;
    //         }
    //     }

    //     while (true) {
    //         System.out.println("Digite o email: ");
    //         email = new Scanner(System.in).nextLine();
    //         if (email.isEmpty()) {
    //             System.out.println("Campo obrigatorio!");
    //         } else {
    //             break;
    //         }
    //     }

    //     Administrador novoAdministrador = new Administrador(idAdministrador, nome, senhaString, email);
    //     System.out.println("Novo administrador cadastrado com sucesso!\n");
    //     return novoAdministrador;
    // }
}
