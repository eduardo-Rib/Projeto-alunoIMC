package com.example.projetoalunoimc.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Colunas {

    private int id;
    private String nome;

    public Colunas(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ObservableList carregarColunas() {
        List<Colunas> colunas = new ArrayList<>();
        ObservableList<Colunas> obsColunas;

        colunas.clear();
        Colunas coluna1 = new Colunas(1, "CPF");
        Colunas coluna2 = new Colunas(2, "Nome");
        Colunas coluna3 = new Colunas(3, "DataNascimento");
        Colunas coluna4 = new Colunas(4, "peso");
        Colunas coluna5 = new Colunas(5, "altura");

        colunas.add(coluna1);
        colunas.add(coluna2);
        colunas.add(coluna3);
        colunas.add(coluna4);
        colunas.add(coluna5);

        obsColunas = FXCollections.observableArrayList(colunas);

        return obsColunas;
    }
}
