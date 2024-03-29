package com.example.clinicaws.services;

import com.example.clinicaws.exceptions.ValidacaoException;
import com.example.clinicaws.model.Especialidade;
import com.example.clinicaws.repositories.EspecialidadeRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class EspecialidadeService {
    public EspecialidadeService() {
    }
    public ArrayList<Especialidade> findAll() throws SQLException {
        return new EspecialidadeRepository().findAll();
    }
    public Especialidade findById(int id) throws SQLException {
        return new EspecialidadeRepository().findById(id);
    }
    public void delete(int id) throws SQLException{
        new EspecialidadeRepository().delete(id);
    }
    public Especialidade insert(Especialidade especialidade) throws SQLException, ValidacaoException {
        return new EspecialidadeRepository().insert(especialidade);
    }
    public Especialidade update(Especialidade especialidade) throws SQLException, ValidacaoException{
        return new EspecialidadeRepository().update(especialidade);
    }
}
