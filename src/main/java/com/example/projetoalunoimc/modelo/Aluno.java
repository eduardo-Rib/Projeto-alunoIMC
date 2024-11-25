package com.example.projetoalunoimc.modelo;

import com.example.projetoalunoimc.dao.Crud;

import java.sql.SQLException;
import java.util.List;

public class Aluno {
    private String cpf;
    private String nome;
    private String dataNascimento;
    private float peso;
    private float altura;

    public Aluno(String cpf, String nome, String dataNascimento, float peso, float altura) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public static void inserirAluno(Aluno aluno){
        String sql= "INSERT INTO aluno (cpf, nome, dataNascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
        Crud.insertDeleteUpdate(sql, aluno, 1);
    }

    public static void excluirAluno(Aluno aluno){
        String sql= "DELETE FROM aluno WHERE cpf = ?";
        Crud.insertDeleteUpdate(sql, aluno, 2);
    }

    public static void atualizarAluno(Aluno aluno){
        String sql= "UPDATE aluno SET nome = ?, dataNascimento = ?, peso = ?, altura = ? WHERE cpf = ?";
        Crud.insertDeleteUpdate(sql, aluno, 3);
    }

    public static List<Aluno> consultarAluno(Aluno aluno, int n) throws SQLException {
        String sql= "SELECT * FROM aluno WHERE cpf = ?";

        if (n != 1){
            sql = "SELECT * FROM aluno";
        }
        return Crud.select(sql, aluno, n);
    }
}
