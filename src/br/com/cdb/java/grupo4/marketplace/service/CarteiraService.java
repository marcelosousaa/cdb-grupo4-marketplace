package br.com.cdb.java.grupo4.marketplace.service;

import java.util.List;
import java.util.Scanner;
import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;

public class CarteiraService {
    public static void gerenciarCarteira(Cliente cliente, List<Usuario> listaDeUsuarios) {
        boolean running = true;
        while (running) {
            System.out.println("\n######## Gerenciamento de Carteira ########");
            System.out.println("Selecione uma opcao:\n"
                    + "1 - Adicionar saldo\n"
                    + "2 - Visualizar saldo\n"
                    + "0 - Voltar ao menu anterior");

            int opcao = new Scanner(System.in).nextInt();
            switch (opcao) {
                case 1:
                    adicionarSaldo(cliente, listaDeUsuarios);
                    break;
                case 2:
                    System.out.println("Saldo atual: R$" + cliente.getSaldo());
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static void adicionarSaldo(Cliente cliente, List<Usuario> listaDeUsuarios) {
        System.out.println("Digite o valor a ser adicionado:");
        double valor = new Scanner(System.in).nextDouble();

        // Adicionar o saldo ao cliente
        cliente.setSaldo(cliente.getSaldo() + valor);

        // Atualizar o saldo do cliente na listaDeUsuarios
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario instanceof Cliente && usuario.getId() == cliente.getId()) {
                ((Cliente) usuario).setSaldo(cliente.getSaldo());
                break; // Não é necessário continuar procurando
            }
        }

        System.out.println("Saldo atualizado com sucesso. Novo saldo: R$" + cliente.getSaldo());
    }
}
