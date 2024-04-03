package com.example.clinicaws.dto.consulta;

public class ConsultaDTO {
    private int id;
    private int pacienteId;
    private int medicoId;

    private String data;
    private String obsCancelamento;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId){
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public ConsultaDTO() {
    }
}

