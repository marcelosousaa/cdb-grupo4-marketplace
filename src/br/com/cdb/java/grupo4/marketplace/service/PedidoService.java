package br.com.cdb.java.grupo4.marketplace.service;

import java.util.List;

import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Pedido;
import br.com.cdb.java.grupo4.marketplace.model.Produto;

public class PedidoService {

    public static Pedido criarPedido(Cliente cliente, List<Produto> carrinho, List<Produto> listaDePedidos){
        Pedido pedido = null;
        int totalDePedidos = 0;
        long idPedido = 0l;

        totalDePedidos = listaDePedidos.size();
        idPedido = totalDePedidos + 1;

        pedido = new Pedido(idPedido, cliente, carrinho);
        return pedido;
    }

}
