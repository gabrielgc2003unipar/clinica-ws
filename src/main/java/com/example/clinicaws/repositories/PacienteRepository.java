package com.example.clinicaws.repositories;

import com.example.clinicaws.infra.ConnectionFactory;
import com.example.clinicaws.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteRepository {
    private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    public PacienteRepository() {
    }
    public ArrayList<Paciente> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "SELECT * FROM paciente order by NM_PACIENTE ASC";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setNome(rs.getString("NM_PACIENTE"));
                paciente.setEmail(rs.getString("DS_EMAIL"));
                paciente.setCpf(rs.getString("NR_CPF"));
                pacientes.add(paciente);
            }
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
        return pacientes;
    }
    public Paciente findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "SELECT * FROM paciente WHERE ID_PACIENTE = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setNome(rs.getString("NM_PACIENTE"));
                paciente.setEmail(rs.getString("DS_EMAIL"));
                paciente.setCpf(rs.getString("NR_CPF"));

                return paciente;
            }
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

    public void delete(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE PACIENTE SET ST_ATIVO = 'N' WHERE ID_PACIENTE = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
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
    public Paciente insert(Paciente paciente) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "INSERT INTO paciente (NM_PACIENTE, NR_TELEFONE, ST_ATIVO, DS_EMAIL, NR_CPF, ID_ENDERECO) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getTelefone());
            ps.setString(3, paciente.getAtivo());
            ps.setString(4, paciente.getEmail());
            ps.setString(5, paciente.getCpf());
            ps.setInt(6, new EnderecoRepository().insert(paciente.getEndereco()).getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
            return paciente;
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
    public Paciente update(Paciente paciente) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE paciente SET NM_PACIENTE = ?, NR_TELEFONE = ?, ST_ATIVO = ?, DS_EMAIL = ?, ID_ENDERECO = ? WHERE ID_PACIENTE = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getTelefone());
            ps.setString(3, paciente.getAtivo());
            ps.setString(4, paciente.getEmail());
            ps.setInt(5, paciente.getEndereco().getId());
            ps.setInt(6, paciente.getId());
            ps.executeUpdate();
            return paciente;
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
        return null;
    }
}
