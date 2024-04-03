package com.example.clinicaws.repositories;

import com.example.clinicaws.infra.ConnectionFactory;
import com.example.clinicaws.model.Endereco;
import com.example.clinicaws.model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicoRepository {
    private ArrayList<Medico> resultado = new ArrayList<Medico>();
    public MedicoRepository() {
    }
    public ArrayList<Medico> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "SELECT * FROM medico ORDER BY NM_MEDICO ASC";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico();
                medico.setNome(rs.getString("NM_MEDICO"));
                medico.setEmail(rs.getString("DS_EMAIL"));
                medico.setCrm(rs.getString("NR_CRM"));
                medico.setAtivo(rs.getString("ST_ATIVO"));
                medico.setEspecialidade(new EspecialidadeRepository().findById(rs.getInt("ID_ESPECIALIDADE")));
                resultado.add(medico);
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
        return resultado;
    }
    public Medico findById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "SELECT * FROM medico WHERE ID_MEDICO = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("ID_MEDICO"));
                medico.setAtivo(rs.getString("ST_ATIVO"));
                medico.setNome(rs.getString("NM_MEDICO"));
                medico.setEmail(rs.getString("DS_EMAIL"));
                medico.setCrm(rs.getString("NR_CRM"));
                medico.setEspecialidade(new EspecialidadeRepository().findById(rs.getInt("ID_ESPECIALIDADE")));
                return medico;
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
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE medico SET ST_ATIVO = 'N' WHERE ID_MEDICO = ?";
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
    public Medico insert(Medico medico) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "INSERT INTO medico (NM_MEDICO, NR_CRM, NR_TELEFONE, ST_ATIVO, DS_EMAIL, ID_ENDERECO, ID_ESPECIALIDADE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getCrm());
            ps.setString(3, medico.getTelefone());
            ps.setString(4, medico.getAtivo());
            ps.setString(5, medico.getEmail());
            ps.setInt(6, new EnderecoRepository().insert(medico.getEndereco()).getId());
            ps.setInt(7, medico.getEspecialidade().getId());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                medico.setId(rs.getInt(1));
            }
            return medico;

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
    public Medico update(Medico medico) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE medico SET NM_MEDICO = ?, NR_CRM = ?, NR_TELEFONE = ?, DS_EMAIL = ?, ID_ENDERECO = ?, ID_ESPECIALIDADE = ? WHERE ID_MEDICO = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getCrm());
            ps.setString(3, medico.getTelefone());
            ps.setString(4, medico.getEmail());
            ps.setInt(5, medico.getEndereco().getId());
            ps.setInt(6, medico.getEspecialidade().getId());
            ps.setInt(7, medico.getId());
            ps.executeUpdate();
            return medico;
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
