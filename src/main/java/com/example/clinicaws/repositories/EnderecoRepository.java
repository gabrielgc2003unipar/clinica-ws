package com.example.clinicaws.repositories;

import com.example.clinicaws.infra.ConnectionFactory;
import com.example.clinicaws.model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnderecoRepository {

    public Endereco findById(int idEndereco) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM endereco WHERE ID_ENDERECO = ?";

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idEndereco);
            rs = ps.executeQuery();
            if (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("ID_ENDERECO"));
                endereco.setLogradouro(rs.getString("DS_LOGRADOURO"));
                endereco.setNumero(rs.getString("NR_ENDERECO"));
                endereco.setBairro(rs.getString("DS_BAIRRO"));
                endereco.setComplemento(rs.getString("DS_COMPLEMENTO"));
                endereco.setCep(rs.getString("NR_CEP"));
                endereco.setCidade(rs.getString("DS_CIDADE"));
                endereco.setUf(rs.getString("DS_UF"));
                return endereco;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Endereco insert(Endereco endereco) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "INSERT INTO endereco (DS_LOGRADOURO, NR_ENDERECO, DS_BAIRRO, DS_COMPLEMENTO, NR_CEP, DS_CIDADE, DS_UF) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getComplemento());
            ps.setString(5, endereco.getCep());
            ps.setString(6, endereco.getCidade());
            ps.setString(7, endereco.getUf());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                endereco.setId(rs.getInt(1));
            }
            return endereco;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
