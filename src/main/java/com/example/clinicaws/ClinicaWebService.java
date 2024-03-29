package com.example.clinicaws;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.clinicaws.exceptions.ValidacaoException;
import com.example.clinicaws.interfaces.IClinicaWebService;
import com.example.clinicaws.model.Consulta;
import com.example.clinicaws.model.Especialidade;
import com.example.clinicaws.model.Medico;
import com.example.clinicaws.model.Paciente;
import com.example.clinicaws.services.ConsultaService;
import com.example.clinicaws.services.EspecialidadeService;
import com.example.clinicaws.services.MedicoService;
import com.example.clinicaws.services.PacienteService;
import jakarta.jws.WebService;


@WebService(endpointInterface = "com.example.clinicaws.interfaces.IClinicaWebService")
public class ClinicaWebService implements IClinicaWebService {

    @Override
    public ArrayList<Medico> findAllMedico() {
        try {
            return new MedicoService().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medico findByIdMedico(int id) {
        try {
            return new MedicoService().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteMedico(int id) {
        try {
            new MedicoService().delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Medico insertMedico(Medico medico) {
        try {
            return new MedicoService().insert(medico);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medico updateMedico(Medico medico) {
        try {
            return new MedicoService().update(medico);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Paciente> findAllPaciente() {
        try {
            return new PacienteService().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paciente findByIdPaciente(int id) {
        try {
            return new PacienteService().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletePaciente(int id) {
        try {
            new PacienteService().delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente insertPaciente(Paciente paciente) {
        try {
            return new PacienteService().insert(paciente);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Paciente updatePaciente(Paciente paciente) {
        try {
            return new PacienteService().update(paciente);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Especialidade> findAllEspecialidade() {
        try {
            return new EspecialidadeService().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Especialidade findByIdEspecialidade(int id) {
        try {
            return new EspecialidadeService().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteEspecialidade(int id) {
        try {
            new EspecialidadeService().delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Especialidade insertEspecialidade(Especialidade especialidade)  {
        try {
            return new EspecialidadeService().insert(especialidade);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Especialidade updateEspecialidade(Especialidade especialidade) {
        try {
            return new EspecialidadeService().update(especialidade);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Consulta> findAllConsulta() {
        try {
            return new ConsultaService().findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consulta findByIdConsulta(int id) {
        try {
            return new ConsultaService().findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteConsulta(int id, String obs) {
        try {
            new ConsultaService().delete(id, obs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ValidacaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consulta insertConsulta(Consulta consulta) {
        try {
            return new ConsultaService().insert(consulta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ValidacaoException e) {
            throw new RuntimeException(e);
        }
    }
}