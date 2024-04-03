package com.example.clinicaws.repositories;

import com.example.clinicaws.exceptions.ValidacaoException;
import com.example.clinicaws.infra.ConnectionFactory;
import com.example.clinicaws.model.Consulta;

import java.sql.*;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class ConsultaRepository {
    public ConsultaRepository() {
    }
    public ArrayList<Consulta> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        ArrayList<Consulta> resultado = new ArrayList<>();
        String query = "SELECT * FROM consulta WHERE ST_CONSULTA = 'A' ORDER BY DT_CONSULTA ASC";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("ID_CONSULTA"));
                consulta.setMedico(new MedicoRepository().findById(rs.getInt("ID_MEDICO")));
                consulta.setPaciente(new PacienteRepository().findById(rs.getInt("ID_PACIENTE")));

                Instant instant = rs.getTimestamp("DT_CONSULTA").toInstant().minus(3, ChronoUnit.HOURS);
                consulta.setData(instant);
                consulta.setStatus(rs.getString("ST_CONSULTA"));
                consulta.setObsCancelamento(rs.getString("DS_OBSCANCELAMENTO"));
                resultado.add(consulta);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    public Consulta findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "SELECT * FROM consulta WHERE ID_CONSULTA = ? AND ST_CONSULTA = 'A'";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("ID_CONSULTA"));
                consulta.setMedico(new MedicoRepository().findById(rs.getInt("ID_MEDICO")));
                consulta.setPaciente(new PacienteRepository().findById(rs.getInt("ID_PACIENTE")));
                Instant instant = rs.getTimestamp("DT_CONSULTA").toInstant();
                consulta.setData(instant);
                consulta.setStatus(rs.getString("ST_CONSULTA"));
                consulta.setObsCancelamento(rs.getString("DS_OBSCANCELAMENTO"));
                return consulta;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void delete(int id, String obs) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE consulta SET ST_CONSULTA = ?, DS_OBSCANCELAMENTO = ? WHERE ID_CONSULTA = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "C");
            ps.setString(2, obs);
            ps.setInt(3, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Consulta insert(Consulta consulta) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "INSERT INTO consulta (ID_MEDICO, ID_PACIENTE, DT_CONSULTA, ST_CONSULTA) VALUES (?, ?, ?, ?)";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, consulta.getMedico().getId());
            ps.setInt(2, consulta.getPaciente().getId());
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(consulta.getData().toString());
            Instant instant = offsetDateTime.toInstant();
            Instant novoInstant = instant.plus(3, ChronoUnit.HOURS);
            Timestamp timestamp = new Timestamp(Date.from(novoInstant).getTime());

            ps.setTimestamp(3, timestamp);
            ps.setString(4, "A");
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                consulta.setId(rs.getInt(1));
            }
            return consulta;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
