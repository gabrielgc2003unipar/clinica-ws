package com.example.clinicaws.services;

import com.example.clinicaws.exceptions.ValidacaoException;
import com.example.clinicaws.model.Consulta;
import com.example.clinicaws.model.Medico;
import com.example.clinicaws.model.Paciente;
import com.example.clinicaws.repositories.ConsultaRepository;

import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ConsultaService {
    public ConsultaService() {
    }
    public ArrayList<Consulta> findAll() throws SQLException {
        return new ConsultaRepository().findAll();
    }
    public Consulta findById(int id) throws SQLException {
        return new ConsultaRepository().findById(id);
    }
    public void delete(int id, String obs) throws SQLException, ValidacaoException {
        if (obs == null || obs.isEmpty()) {
            throw new ValidacaoException("A observação é obrigatória");
        }
        //Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.
        Consulta consulta = findById(id);
        if (consulta == null) {
            throw new ValidacaoException("Consulta não encontrada");
        }
        if (consulta.getData().isBefore(Instant.from(LocalDate.now().plusDays(1)))) {
            throw new ValidacaoException("A consulta não pode ser cancelada com menos de 24 horas de antecedência");
        }

        new ConsultaRepository().delete(id, obs);
    }

    public Consulta insert(Consulta consulta) throws SQLException, ValidacaoException {
        MedicoService medicoService = new MedicoService();
        PacienteService pacienteService = new PacienteService();
        List<Consulta> consultas = new ConsultaService().findAll();

        ArrayList<Medico> medicos = medicoService.findAll();
        if (medicos.isEmpty()) {
            throw new ValidacaoException("Não existem médicos cadastrados");
        }
        ArrayList<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            throw new ValidacaoException("Não existem pacientes cadastrados");
        }

        if (consulta.getData() == null) {
            throw new ValidacaoException("Data/Hora é obrigatória");
        }
        if (consulta.getData().isBefore(Instant.now())) {
            throw new ValidacaoException("Data/Hora deve ser maior que a data atual");
        }
        if (consulta.getData().equals(Instant.now())) {
            throw new ValidacaoException("Data/Hora deve ser maior que a data atual");
        }


        //Valida dia da semana
        DayOfWeek diaDaSemana = DayOfWeek.from(consulta.getData().atZone(ZoneId.systemDefault()));
        if ( diaDaSemana == DayOfWeek.SUNDAY) {
            throw new ValidacaoException("Não é possível agendar consultas aos finais de semana");
        }
        //Valida horário de atendimento
        LocalTime horaConsulta = LocalTime.ofInstant(consulta.getData(), ZoneId.systemDefault()).plusHours(3);
        if (horaConsulta.getHour() < 7 || horaConsulta.getHour() > 18) {
            throw new ValidacaoException("O horário de atendimento é das 07:00 as 18:00, informe um horário válido");
        }

        // Calcula o horário mínimo para agendar uma consulta
        LocalTime horaAtual = LocalTime.now();
        if (horaConsulta.isAfter(horaAtual.plusMinutes(30))) {
            System.out.println("Consulta agendada com antecedência mínima de 30 minutos.");
        }
        /*
        if (horaAtual.getHour() < 7 || horaAtual.getHour() > 18) {
            throw new ValidacaoException("O horário de atendimento é das 07:00 as 18:00, não é possível realizar agendamento fora do horário de atendimento");
        }
         */


        //A escolha do médico é opcional, sendo que nesse caso o sistema deve escolher
        //aleatoriamente algum médico disponível na data/hora preenchida.

        // Verifica se o médico está especificado na consulta
        if (consulta.getMedico() == null) {
            // Lista para armazenar médicos disponíveis
            List<Medico> medicosDisponiveis = new ArrayList<>();

            // Verifica cada médico disponível
            for (Medico medico : medicos) {
                // Verifica se o médico tem sobreposição de horário com a nova consulta
                if (!temSobreposicaoDeHorario(consulta, consultas, medico)) {
                    // Se não houver sobreposição de horário, adiciona à lista de médicos disponíveis
                    medicosDisponiveis.add(medico);
                }
            }

            // Verifica se há médicos disponíveis para consulta
            if (!medicosDisponiveis.isEmpty()) {
                // Seleciona um médico aleatoriamente dos médicos disponíveis
                Random random = new Random();
                Medico medicoSelecionado = medicosDisponiveis.get(random.nextInt(medicosDisponiveis.size()));

                // Define o médico selecionado na consulta
                consulta.setMedico(medicoSelecionado);
            } else {
                throw new ValidacaoException("Não há médicos disponíveis para a data/hora especificada");
            }
        }

        consulta.setMedico(medicoService.findById(consulta.getMedico().getId()));
        //Não permitir o agendamento de consultas com médicos inativos no sistema;
        if (consulta.getMedico() == null) {
            throw new ValidacaoException("Médico não encontrado");
        } else if (consulta.getMedico().getAtivo().equalsIgnoreCase("N")) {
            throw new ValidacaoException("Médico inativo");
        }

        //Não permitir o agendamento de consultas com médicos que possuem consultas agendadas no mesmo horário;
        if (temSobreposicaoDeHorario(consulta, consultas, consulta.getMedico())) {
            throw new ValidacaoException("Médico já possui consulta agendada neste horário");
        }


        consulta.setPaciente(pacienteService.findById(consulta.getPaciente().getId()));
        //Não permitir o agendamento de consultas com pacientes inativos no sistema;
        if (consulta.getPaciente() == null) {
            throw new ValidacaoException("Paciente não encontrado");
        }else if(consulta.getPaciente().getAtivo().equalsIgnoreCase("N")){
            throw new ValidacaoException("Paciente inativo");
        }

        //Não permitir o agendamento de mais de uma consulta no mesmo dia para um mesmo
        //paciente;
        for (Consulta c : consultas) {
            if (c.getPaciente().getId() == consulta.getPaciente().getId()) {
                ZonedDateTime zonedDateTimeC = c.getData().atZone(ZoneId.systemDefault());
                ZonedDateTime zonedDateTimeConsulta = consulta.getData().atZone(ZoneId.systemDefault());

                if (LocalDate.from(zonedDateTimeC).isEqual(LocalDate.from(zonedDateTimeConsulta))) {
                    throw new ValidacaoException("Paciente já possui consulta agendada para este dia");
                }
            }
        }


        return new ConsultaRepository().insert(consulta);
    }


    private boolean temSobreposicaoDeHorario(Consulta novaConsulta, List<Consulta> consultas, Medico medico) {
        // Obtém a data e hora da nova consulta
        Instant instant = novaConsulta.getData();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC")); // ou qualquer outra zona de fuso horário que você deseje usar
        LocalDateTime inicioNovaConsulta = zonedDateTime.toLocalDateTime();
        LocalDateTime fimNovaConsulta = inicioNovaConsulta.plusHours(1); // Supondo que cada consulta dure 1 hora

        // Verifica cada consulta do médico para verificar se há sobreposição de horário
        for (Consulta consultaExistente : consultas) {
            // Verifica se a consultaExistente é do médico atual
            if (medico.getId() == consultaExistente.getMedico().getId()) {
                // Obtém a data e hora da consultaExistente
                LocalDateTime inicioConsultaExistente = LocalDateTime.ofInstant(consultaExistente.getData(), ZoneId.systemDefault()).plusHours(3);
                LocalDateTime fimConsultaExistente = inicioConsultaExistente.plusHours(1); // Supondo que cada consulta dure 1 hora

                // Verifica se há sobreposição de horário
                if ((inicioNovaConsulta.isBefore(fimConsultaExistente) && fimNovaConsulta.isAfter(inicioConsultaExistente)) ||
                        (inicioNovaConsulta.isEqual(fimConsultaExistente)) || (fimNovaConsulta.isEqual(inicioConsultaExistente))) {
                    // Sobreposição de horário encontrada
                    return true;
                }
            }
        }

        // Não há sobreposição de horário
        return false;
    }
}
