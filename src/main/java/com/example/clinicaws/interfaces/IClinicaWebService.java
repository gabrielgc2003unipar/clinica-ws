package com.example.clinicaws.interfaces;

import com.example.clinicaws.model.Consulta;
import com.example.clinicaws.model.Especialidade;
import com.example.clinicaws.model.Medico;
import com.example.clinicaws.model.Paciente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService
public interface IClinicaWebService {
    @WebMethod
    ArrayList<Medico> findAllMedico();
    @WebMethod
    Medico findByIdMedico(int id);
    @WebMethod
    void deleteMedico(int id);
    @WebMethod
    Medico insertMedico(Medico medico);
    @WebMethod
    Medico updateMedico(Medico medico);

    @WebMethod
    ArrayList<Paciente> findAllPaciente();
    @WebMethod
    Paciente findByIdPaciente(int id);
    @WebMethod
    void deletePaciente(int id);
    @WebMethod
    Paciente insertPaciente(Paciente paciente);
    @WebMethod
    Paciente updatePaciente(Paciente paciente);

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
    ArrayList<Consulta> findAllConsulta();
    @WebMethod
    Consulta findByIdConsulta(int id);
    @WebMethod
    void deleteConsulta(int id, String obs);
    @WebMethod
    Consulta insertConsulta(Consulta consulta);
}
