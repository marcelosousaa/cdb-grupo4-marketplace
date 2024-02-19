package br.com.cdb.java.grupo4.marketplace.service;

import br.com.cdb.java.grupo4.marketplace.model.Cliente;
import br.com.cdb.java.grupo4.marketplace.model.Usuario;

public class UsuarioService {

    public boolean verificarUsuario(Usuario usuario){
        if(usuario instanceof Cliente){
            return true;
        } else{
            return false;
        }
    }

    public boolean login(Usuario usuario){
        if(usuario.getSenha() != null){
            return true;
        } else {
            return false;
        }

    }

    public void cadastrarCliente(Cliente cliente){
        
    }
}
