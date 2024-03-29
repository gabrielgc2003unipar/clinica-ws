package com.example.clinicaws.services;

import com.example.clinicaws.exceptions.ValidacaoException;
import com.example.clinicaws.model.Paciente;
import com.example.clinicaws.repositories.PacienteRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteService {
    public PacienteService() {
    }
    public ArrayList<Paciente> findAll() throws SQLException {
        return new PacienteRepository().findAll();
    }
    public Paciente findById(int id) throws SQLException {
        return new PacienteRepository().findById(id);
    }
    public void delete(int id) throws SQLException{
        new PacienteRepository().delete(id);
    }
    public Paciente insert(Paciente paciente) throws SQLException, ValidacaoException {
        if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new ValidacaoException("O Nome é obrigatório");
        }
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new ValidacaoException("O Email é obrigatório");
        }
        if (paciente.getTelefone() == null || paciente.getTelefone().isEmpty()) {
            throw new ValidacaoException("O Telefone é obrigatório");
        }
        if (paciente.getCpf() == null || paciente.getCpf().isEmpty()) {
            throw new ValidacaoException("O CPF é obrigatório");
        }
        if (paciente.getEndereco() == null) {
            throw new ValidacaoException("O Endereço é obrigatório");
        }
        if (paciente.getEndereco().getId() == 0) {
            throw new ValidacaoException("O Endereço é obrigatório");
        }
        if (paciente.getEndereco().getBairro() == null || paciente.getEndereco().getBairro().isEmpty()) {
            throw new ValidacaoException("O Bairro é obrigatório");
        }
        if (paciente.getEndereco().getCidade() == null || paciente.getEndereco().getCidade().isEmpty()) {
            throw new ValidacaoException("A Cidade é obrigatória");
        }
        if (paciente.getEndereco().getUf() == null || paciente.getEndereco().getUf().isEmpty()) {
            throw new ValidacaoException("O Estado é obrigatório");
        }
        if (paciente.getEndereco().getLogradouro() == null || paciente.getEndereco().getLogradouro().isEmpty()) {
            throw new ValidacaoException("O Logradouro é obrigatório");
        }
        if (paciente.getEndereco().getCep() == null || paciente.getEndereco().getCep().isEmpty()) {
            throw new ValidacaoException("O CEP é obrigatório");
        }
        return new PacienteRepository().insert(paciente);
    }
    public Paciente update(Paciente paciente) throws SQLException, ValidacaoException{
        PacienteService pacienteService = new PacienteService();
        Paciente pacienteOld = pacienteService.findById(paciente.getId());
        if (paciente.getCpf().toUpperCase() != pacienteOld.getCpf().toUpperCase()){
            throw new ValidacaoException("O CPF não pode ser alterado");
        }
        if (paciente.getEmail().toUpperCase() != pacienteOld.getEmail().toUpperCase()){
            throw new ValidacaoException("O Email não pode ser alterado");
        }

        return new PacienteRepository().update(paciente);
    }
}
