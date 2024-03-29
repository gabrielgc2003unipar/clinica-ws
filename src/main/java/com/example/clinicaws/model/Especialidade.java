package com.example.clinicaws.model;

public class Especialidade {
    private int id;
    private String descricao;

    public Especialidade() {
    }

    public Especialidade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Especialidade{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
    String sql = "create table especialidade( id serial primary key, descricao varchar(255) not null);";
}
