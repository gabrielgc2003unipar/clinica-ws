package com.example.clinicaws;

import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;

import com.example.clinicaws.dto.consulta.ConsultaDTO;
import com.example.clinicaws.dto.medico.MedicoDTO;
import com.example.clinicaws.dto.paciente.PacienteDTO;
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
    public ArrayList<MedicoDTO> findAllMedico() {
        try {
            ArrayList<MedicoDTO> medicosDTO = new ArrayList<>();
            ArrayList<Medico> medicos = new MedicoService().findAll();
            for (Medico medico : medicos) {
                MedicoDTO medicoDTO = new MedicoDTO();
                medicoDTO.setNome(medico.getNome());
                medicoDTO.setCrm(medico.getCrm());
                medicoDTO.setEmail(medico.getEmail());
                medicoDTO.setEspecialidade(medico.getEspecialidade().getDescricao());
                medicosDTO.add(medicoDTO);
            }
            return medicosDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MedicoDTO findByIdMedico(int id) {
        try {
            Medico medico = new MedicoService().findById(id);
            MedicoDTO medicoDTO = new MedicoDTO();
            medicoDTO.setNome(medico.getNome());
            medicoDTO.setCrm(medico.getCrm());
            medicoDTO.setEmail(medico.getEmail());
            medicoDTO.setEspecialidade(medico.getEspecialidade().getDescricao());
            return medicoDTO;
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
    public MedicoDTO insertMedico(Medico medico) {
        try {
            MedicoDTO medicoDTO = new MedicoDTO();
            Medico medicoInserido = new MedicoService().insert(medico);
            medicoDTO.setNome(medicoInserido.getNome());
            medicoDTO.setCrm(medicoInserido.getCrm());
            medicoDTO.setEmail(medicoInserido.getEmail());
            medicoDTO.setEspecialidade(medicoInserido.getEspecialidade().getDescricao());
            return medicoDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MedicoDTO updateMedico(Medico medico) {
        try {
            MedicoDTO medicoDTO = new MedicoDTO();
            Medico medicoAtualizado = new MedicoService().update(medico);
            medicoDTO.setNome(medicoAtualizado.getNome());
            medicoDTO.setCrm(medicoAtualizado.getCrm());
            medicoDTO.setEmail(medicoAtualizado.getEmail());
            medicoDTO.setEspecialidade(medicoAtualizado.getEspecialidade().getDescricao());
            return medicoDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<PacienteDTO> findAllPaciente() {
        try {
            ArrayList<PacienteDTO> pacientesDTO = new ArrayList<>();
            ArrayList<Paciente> pacientes = new PacienteService().findAll();
            for (Paciente paciente : pacientes) {
                PacienteDTO pacienteDTO = new PacienteDTO();
                pacienteDTO.setNome(paciente.getNome());
                pacienteDTO.setCpf(paciente.getCpf());
                pacienteDTO.setEmail(paciente.getEmail());
                pacientesDTO.add(pacienteDTO);
            }
            return pacientesDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PacienteDTO findByIdPaciente(int id) {
        try {
            Paciente paciente = new PacienteService().findById(id);
            PacienteDTO pacienteDTO = new PacienteDTO();
            pacienteDTO.setNome(paciente.getNome());
            pacienteDTO.setCpf(paciente.getCpf());
            pacienteDTO.setEmail(paciente.getEmail());
            return pacienteDTO;
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
    public PacienteDTO insertPaciente(Paciente paciente) {
        try {
            PacienteDTO pacienteDTO = new PacienteDTO();
            Paciente pacienteInserido = new PacienteService().insert(paciente);
            pacienteDTO.setNome(pacienteInserido.getNome());
            pacienteDTO.setCpf(pacienteInserido.getCpf());
            pacienteDTO.setEmail(pacienteInserido.getEmail());
            return pacienteDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidacaoException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public PacienteDTO updatePaciente(Paciente paciente) {
        try {
            PacienteDTO pacienteDTO = new PacienteDTO();
            Paciente pacienteAtualizado = new PacienteService().update(paciente);
            pacienteDTO.setNome(pacienteAtualizado.getNome());
            pacienteDTO.setCpf(pacienteAtualizado.getCpf());
            pacienteDTO.setEmail(pacienteAtualizado.getEmail());
            return pacienteDTO;
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
    public ArrayList<ConsultaDTO> findAllConsulta() {
        try {
            ArrayList<ConsultaDTO> consultasDTO = new ArrayList<>();
            ArrayList<Consulta> consultas = new ConsultaService().findAll();
            for (Consulta consulta : consultas) {
                ConsultaDTO consultaDTO = new ConsultaDTO();
                consultaDTO.setId(consulta.getId());
                consultaDTO.setData(consulta.getData().toString());
                consultaDTO.setMedicoId(consulta.getMedico().getId());
                consultaDTO.setPacienteId(consulta.getPaciente().getId());
                consultaDTO.setStatus(consulta.getStatus());
                consultaDTO.setObsCancelamento(consulta.getObsCancelamento());
                consultasDTO.add(consultaDTO);
            }
            return consultasDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ConsultaDTO findByIdConsulta(int id) {
        try {
            Consulta consulta = new ConsultaService().findById(id);
            ConsultaDTO consultaDTO = new ConsultaDTO();
            consultaDTO.setId(consulta.getId());
            consultaDTO.setData(consulta.getData().toString());
            consultaDTO.setMedicoId(consulta.getMedico().getId());
            consultaDTO.setPacienteId(consulta.getPaciente().getId());
            consultaDTO.setStatus(consulta.getStatus());
            consultaDTO.setObsCancelamento(consulta.getObsCancelamento());
            return consultaDTO;
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
    public ConsultaDTO insertConsulta(ConsultaDTO consulta) {
        Consulta consultaModel = new Consulta();
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(consulta.getData());
        Instant instant = offsetDateTime.toInstant();
        consultaModel.setData(instant);
        try {
            consultaModel.setMedico(new MedicoService().findById(consulta.getMedicoId()));
            consultaModel.setPaciente(new PacienteService().findById(consulta.getPacienteId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        consultaModel.setStatus(consulta.getStatus());
        consultaModel.setObsCancelamento(consulta.getObsCancelamento());
        consultaModel.setId(consulta.getId());
        try {
            ConsultaDTO consultaDTO = new ConsultaDTO();
            Consulta consultaInserida = new ConsultaService().insert(consultaModel);
            consultaDTO.setId(consultaInserida.getId());
            consultaDTO.setData(consultaInserida.getData().toString());
            consultaDTO.setMedicoId(consultaInserida.getMedico().getId());
            consultaDTO.setPacienteId(consultaInserida.getPaciente().getId());
            consultaDTO.setStatus(consultaInserida.getStatus());
            consultaDTO.setObsCancelamento(consultaInserida.getObsCancelamento());
            return consultaDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ValidacaoException e) {
            throw new RuntimeException(e);
        }
    }
}