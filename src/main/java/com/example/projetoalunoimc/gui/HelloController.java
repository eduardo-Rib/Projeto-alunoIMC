package com.example.projetoalunoimc.gui;
import com.example.projetoalunoimc.modelo.Aluno;
import com.example.projetoalunoimc.modelo.Colunas;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    private TableView<Aluno> tableView;

    @FXML
    private TableColumn<Aluno, String> columnCpf;

    @FXML
    private TableColumn<Aluno, String> columnNome;

    @FXML
    private TableColumn<Aluno, String> columnDataNascimento;

    @FXML
    private TableColumn<Aluno, Float> columnPeso;

    @FXML
    private TableColumn<Aluno, Float> columnAltura;


    @FXML
    private TextField cpf;

    @FXML
    private TextField nome;

    @FXML
    private TextField dataNascimento;

    @FXML
    private TextField peso;

    @FXML
    private TextField altura;

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnCarregar;

    @FXML
    private Button btnExcluir;

    @FXML
    public void initialize() throws SQLException {
        // Configurando as colunas
        /*columnCpf.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCpf()));
        columnNome.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNome()));
        columnDataNascimento.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDataNascimento()));
        columnPeso.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPeso()));
        columnAltura.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAltura()));*/

        // Inicializando a lista de alunos
        ObservableList<Aluno> alunos;
        alunos = FXCollections.observableArrayList(Aluno.consultarAluno(null, 2));
        tableView.setItems(alunos);



        // Inicializações ou ações ao carregar a interface
        btnIncluir.setOnAction(e -> System.out.println("Incluir clicado!"));
        btnEditar.setOnAction(e -> System.out.println("Editar clicado!"));
        btnCarregar.setOnAction(e -> System.out.println("Carregar clicado!"));
        btnExcluir.setOnAction(e -> System.out.println("Excluir clicado!"));
    }



}