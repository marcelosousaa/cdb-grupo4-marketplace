package br.com.cdb.java.grupo4.marketplace.util;

import java.util.InputMismatchException;

public class Validacoes {

    public boolean validaString(String string){

        String stringValidada;

        try{
            stringValidada = string;
        }catch(InputMismatchException e){
            
        }

        return true;
    }

}
