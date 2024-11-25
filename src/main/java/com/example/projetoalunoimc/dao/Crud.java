package com.example.projetoalunoimc.dao;

import com.example.projetoalunoimc.factory.Conexao;
import com.example.projetoalunoimc.modelo.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Crud {

    public void insertDeleteUpdate(String sql, Aluno aluno, int n){
        Connection con = new Conexao().conectar();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
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

    public List<Aluno> select(String sql, Aluno aluno) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        Connection con = new Conexao().conectar();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, aluno.getCpf());

            System.out.println(stmt);

            try (ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    Aluno aux = new Aluno(result.getString("cpf"), result.getString("nome"), result.getString("dataNascimento"), result.getFloat("peso"), result.getFloat("altura"));
                    alunos.add(aux);
                }
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            } finally {
                stmt.close();
            }

            return alunos;
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}