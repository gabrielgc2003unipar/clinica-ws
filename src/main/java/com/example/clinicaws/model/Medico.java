package com.example.clinicaws.model;

public class Medico extends Pessoa{
    private String crm;
    private Especialidade especialidade;

    public Medico() {
    }

    public Medico(int id, String nome, String email, String telefone, String ativo, Endereco endereco, String crm, Especialidade especialidade) {
        super(id, nome, email, telefone, ativo, endereco);
        this.crm = crm;
        this.especialidade = especialidade;
    }
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    String sql = "create table medico( id_medico serial primary key, nome varchar(255) not null, email varchar(255) not null, telefone varchar(255) not null, ativo varchar(1) not null, id_endereco integer not null, crm varchar(255) not null, id_especialidade integer not null, foreign key (id_endereco) references endereco(id), foreign key (id_especialidade) references especialidade(id));";
}
