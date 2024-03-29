package com.example.clinicaws.repositories;

import com.example.clinicaws.infra.ConnectionFactory;
import com.example.clinicaws.model.Especialidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspecialidadeRepository {
    private ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
    public EspecialidadeRepository() {
    }
    public ArrayList<Especialidade> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "SELECT * FROM especialidade";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Especialidade especialidade = new Especialidade();
                especialidade.setId(rs.getInt("ID_ESPECIALIDADE"));
                especialidade.setDescricao(rs.getString("DS_ESPECIALIDADE"));
                especialidades.add(especialidade);
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
        return especialidades;
    }
    public Especialidade findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "SELECT * FROM especialidade WHERE ID_ESPECIALIDADE = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Especialidade especialidade = new Especialidade();
                especialidade.setId(rs.getInt("ID_ESPECIALIDADE"));
                especialidade.setDescricao(rs.getString("DS_ESPECIALIDADE"));
                return especialidade;
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
        String query = "DELETE FROM especialidade WHERE ID_ESPECIALIDADE = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
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
    public Especialidade insert(Especialidade especialidade) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "INSERT INTO especialidade (ds_especialidade) VALUES (?)";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, especialidade.getDescricao());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                especialidade.setId(rs.getInt(1));
            }
            return especialidade;
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
    public Especialidade update(Especialidade especialidade) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs    = null;
        String query = "UPDATE especialidade SET DS_ESPECIALIDADE = ? WHERE ID_ESPECIALIDADE = ?";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, especialidade.getDescricao());
            ps.setInt(2, especialidade.getId());
            ps.executeUpdate();
            return especialidade;
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
