package com.example.clinicaws.services;

import com.example.clinicaws.exceptions.ValidacaoException;
import com.example.clinicaws.model.Medico;
import com.example.clinicaws.repositories.MedicoRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class MedicoService {
    public MedicoService() {
    }

    public ArrayList<Medico> findAll() throws SQLException {
        return new MedicoRepository().findAll();
    }
    public Medico findById(int id) throws SQLException{
        return new MedicoRepository().findById(id);
    }
    public void delete(int id) throws SQLException{
        new MedicoRepository().delete(id);
    }
    public Medico insert(Medico medico) throws SQLException, ValidacaoException {
        if (medico.getCrm() == null || medico.getCrm().isEmpty()) {
            throw new ValidacaoException("O CRM é obrigatório");
        }
        if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new ValidacaoException("O Nome é obrigatório");
        }
        if (medico.getEmail() == null || medico.getEmail().isEmpty()) {
            throw new ValidacaoException("O Email é obrigatório");
        }
        if (medico.getTelefone() == null || medico.getTelefone().isEmpty()) {
            throw new ValidacaoException("O Telefone é obrigatório");
        }
        if (medico.getEspecialidade() == null) {
            throw new ValidacaoException("A Especialidade é obrigatória");
        }
        if (medico.getEndereco() == null) {
            throw new ValidacaoException("O Endereço é obrigatório");
        }
        if (medico.getEspecialidade().getId() == 0) {
            throw new ValidacaoException("A Especialidade é obrigatória");
        }
        if (medico.getEndereco().getId() == 0) {
            throw new ValidacaoException("O Endereço é obrigatório");
        }
        if (medico.getEndereco().getBairro() == null || medico.getEndereco().getBairro().isEmpty()) {
            throw new ValidacaoException("O Bairro é obrigatório");
        }
        if (medico.getEndereco().getCidade() == null || medico.getEndereco().getCidade().isEmpty()) {
            throw new ValidacaoException("A Cidade é obrigatória");
        }
        if (medico.getEndereco().getUf() == null || medico.getEndereco().getUf().isEmpty()) {
            throw new ValidacaoException("O Estado é obrigatório");
        }
        if (medico.getEndereco().getLogradouro() == null || medico.getEndereco().getLogradouro().isEmpty()) {
            throw new ValidacaoException("O Logradouro é obrigatório");
        }
        if (medico.getEndereco().getCep() == null || medico.getEndereco().getCep().isEmpty()) {
            throw new ValidacaoException("O CEP é obrigatório");
        }
        return new MedicoRepository().insert(medico);
    }
    public Medico update(Medico medico) throws SQLException, ValidacaoException{
        MedicoService medicoService = new MedicoService();
        Medico medicoOld = medicoService.findById(medico.getId());
        if (medico.getCrm().toUpperCase() != medicoOld.getCrm().toUpperCase()) {
            throw new ValidacaoException("O CRM não pode ser alterado");
        }
        if (medico.getEmail().toUpperCase() != medicoOld.getEmail().toUpperCase()) {
            throw new ValidacaoException("O Email não pode ser alterado");
        }
        if (medico.getEspecialidade().getId() != medicoOld.getEspecialidade().getId()) {
            throw new ValidacaoException("A especialidade não pode ser alterada");
        }
        return new MedicoRepository().update(medico);
    }
}
