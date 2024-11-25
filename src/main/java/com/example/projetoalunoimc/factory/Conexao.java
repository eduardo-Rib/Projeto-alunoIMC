package com.example.projetoalunoimc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection conectar() {
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/alunoimc","root","eduardo");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
