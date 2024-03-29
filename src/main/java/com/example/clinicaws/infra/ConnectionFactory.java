package com.example.clinicaws.infra;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionFactory {
    private static final String RESOURCE_NAME = "postgresResource";
    private DataSource getDataSource() throws NamingException {
        Context context = new InitialContext();
        return (DataSource) context.lookup(RESOURCE_NAME);
    }
    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (NamingException | java.sql.SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados" + e.getMessage());
        }
        return null;
    }
}
