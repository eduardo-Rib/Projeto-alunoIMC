package com.example.projetoalunoimc.dao;

import com.example.projetoalunoimc.factory.Conexao;
import com.example.projetoalunoimc.modelo.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Crud {

    public void insertDeleteUpdate(String sql, Aluno aluno, int n){
        Connection con = new Conexao().conectar();
        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            if (n == 2){
                stmt.setString(1, aluno.getCpf());
            }else{
                stmt.setString(1, aluno.getCpf());
                stmt.setString(1, aluno.getNome());
                stmt.setString(1, aluno.getDataNascimento());
                stmt.setFloat(1, aluno.getPeso());
                stmt.setFloat(1, aluno.getAltura());
            }

            System.out.println(stmt);
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Erro com o banco de dados", exception);
        }
    }

    

}
