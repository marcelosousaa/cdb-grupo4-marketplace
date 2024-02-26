package br.com.cdb.java.grupo4.marketplace.util;

public class ValidatorUtil {

    public ValidatorUtil() {
    }

    public boolean validaTelefone(String telefone) {
        String telefonePattern = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}";
        return telefone.matches(telefonePattern);
    }

    public boolean validaEmail(String email) {
        String emailPattern = ".+@.+[.].+";
        return email.matches(emailPattern);
    }

}
