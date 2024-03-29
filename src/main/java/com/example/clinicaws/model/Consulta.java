package com.example.clinicaws.model;

import java.time.Instant;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private Instant data;
    private String obsCancelamento;
    private String status;

    public Consulta() {
    }

    public Consulta(int id, Paciente paciente, Medico medico, Instant data, String obsCancelamento, String status) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.obsCancelamento = obsCancelamento;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getObsCancelamento() {
        return obsCancelamento;
    }

    public void setObsCancelamento(String obsCancelamento) {
        this.obsCancelamento = obsCancelamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
