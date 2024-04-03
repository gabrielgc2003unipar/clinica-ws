package com.example.clinicaws.interfaces;

import com.example.clinicaws.dto.consulta.ConsultaDTO;
import com.example.clinicaws.dto.medico.MedicoDTO;
import com.example.clinicaws.dto.paciente.PacienteDTO;
import com.example.clinicaws.model.Especialidade;
import com.example.clinicaws.model.Medico;
import com.example.clinicaws.model.Paciente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService
public interface IClinicaWebService {
    @WebMethod
    ArrayList<MedicoDTO> findAllMedico();
    @WebMethod
    MedicoDTO findByIdMedico(int id);
    @WebMethod
    void deleteMedico(int id);
    @WebMethod
    MedicoDTO insertMedico(Medico medico);
    @WebMethod
    MedicoDTO updateMedico(Medico medico);

    @WebMethod
    ArrayList<PacienteDTO> findAllPaciente();
    @WebMethod
    PacienteDTO findByIdPaciente(int id);
    @WebMethod
    void deletePaciente(int id);
    @WebMethod
    PacienteDTO insertPaciente(Paciente paciente);
    @WebMethod
    PacienteDTO updatePaciente(Paciente paciente);

    @WebMethod
    ArrayList<Especialidade> findAllEspecialidade();
    @WebMethod
    Especialidade findByIdEspecialidade(int id);
    @WebMethod
    void deleteEspecialidade(int id);
    @WebMethod
    Especialidade insertEspecialidade(Especialidade especialidade);
    @WebMethod
    Especialidade updateEspecialidade(Especialidade especialidade);
    @WebMethod
    ArrayList<ConsultaDTO> findAllConsulta();
    @WebMethod
    ConsultaDTO findByIdConsulta(int id);
    @WebMethod
    void deleteConsulta(int id, String obs);
    @WebMethod
    ConsultaDTO insertConsulta(ConsultaDTO consulta);
}
