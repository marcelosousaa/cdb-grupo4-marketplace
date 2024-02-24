package br.com.cdb.java.grupo4.marketplace.model;

public final class Cliente extends Usuario {
    private String telefone;
    private String endereco;
    private String dataDeNascimento;
    private double saldo;

    public Cliente(long id, String nome, String senha, String email, String telefone, String endereco, String dataDeNascimento) {
        super(id, nome, senha, email, 'C');
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataDeNascimento = dataDeNascimento;
        this.saldo += 100;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return this.getNome()
        + " - " + this.getSaldo()
        + " - " + this.getDataDeNascimento()
        + " - " + this.getEmail()
        + " - " + this.getTelefone()
        + " - " + this.getEndereco();
    }
}
