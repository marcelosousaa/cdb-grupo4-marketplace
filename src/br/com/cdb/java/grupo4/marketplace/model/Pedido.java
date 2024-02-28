package br.com.cdb.java.grupo4.marketplace.model;

import java.util.List;

public class Pedido {

   private long id;
   private Cliente cliente;
   private List<Produto> listaDeProdutosComprados;

    public Pedido (long id, Cliente cliente, List<Produto> listaDeProdutosComprados){
        this.id = id;
        this.cliente = cliente;
        this.listaDeProdutosComprados = listaDeProdutosComprados;
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Produto> getListaDeProdutosComprados() {
        return listaDeProdutosComprados;
    }
}
