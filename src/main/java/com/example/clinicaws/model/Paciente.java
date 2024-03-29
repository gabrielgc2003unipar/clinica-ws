package com.example.clinicaws.model;

public class Paciente extends Pessoa{

    private String cpf;

    public Paciente() {
    }

    public Paciente(int id, String nome, String email, String telefone, String ativo, Endereco endereco, String cpf) {
        super(id, nome, email, telefone, ativo, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    String sql = "create table paciente( id_paciente serial primary key, nome varchar(255) not null, email varchar(255) not null, telefone varchar(255) not null, ativo varchar(1) not null, id_endereco integer not null, cpf varchar(255) not null, foreign key (id_endereco) references endereco(id));";
}
