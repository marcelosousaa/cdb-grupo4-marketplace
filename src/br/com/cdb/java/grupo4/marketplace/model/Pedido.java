package br.com.cdb.java.grupo4.marketplace.model;

import java.util.List;

public class Pedido {

    long id;
    Cliente cliente;
    List<Produto> listaDeProdutosComprados;

    public Pedido (long id, Cliente cliente, List<Produto> listaDeProdutosComprados){
        this.cliente = cliente;
        this.listaDeProdutosComprados = listaDeProdutosComprados;
    }

}
