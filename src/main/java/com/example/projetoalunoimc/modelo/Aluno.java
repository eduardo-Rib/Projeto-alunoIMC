package com.example.projetoalunoimc.modelo;

import com.example.projetoalunoimc.dao.Crud;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Aluno {
    private int id;
    private String cpf;
    private String nome;
    private String dataNascimento;
    private float peso;
    private float altura;

    public Aluno(int id, String cpf, String nome, String dataNascimento, float peso, float altura) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        String sql= "DELETE FROM aluno WHERE id = ?";
        Crud.insertDeleteUpdate(sql, aluno, 2);
    }

    public static void atualizarAluno(Aluno aluno){
        String sql= "UPDATE aluno SET cpf = ?, nome = ?, dataNascimento = ?, peso = ?, altura = ? WHERE id = ?";
        Crud.insertDeleteUpdate(sql, aluno, 3);
    }

    public static List<Aluno> consultarAluno(Aluno aluno) throws SQLException {
        String sql= "SELECT * FROM aluno";
        return Crud.select(sql, aluno);
    }

    public void calculaIMC() {
        double imc = peso / (altura * altura);
        String interpretacao = interpretarIMC(imc);
        String dataAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Nome do arquivo por CPF
        String nomeArquivo = cpf + ".txt";

        // Registro do cálculo
        String registro = String.format(
                "Data: %s%nCPF: %s%nNome: %s%nIMC: %.2f%nInterpretação: %s%n%n",
                dataAtual, cpf, nome, imc, interpretacao
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(registro);
            System.out.println("Cálculo gravado com sucesso no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }

    private String interpretarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade Grau 1";
        } else if (imc < 39.9) {
            return "Obesidade Grau 2";
        } else {
            return "Obesidade Grau 3";
        }
    }

}
