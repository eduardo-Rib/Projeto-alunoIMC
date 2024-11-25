package com.example.projetoalunoimc.gui;
import com.example.projetoalunoimc.modelo.Aluno;
import com.example.projetoalunoimc.modelo.Colunas;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
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

    private ObservableList<Aluno> obsAlunos;
    private List<Aluno> alunos = new ArrayList<>();

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
    private Button btnLimpar;

    @FXML
    private Button btnExcluir;


    @FXML
    public void initialize() throws SQLException {
        carregarTableView();

        // Inicializações ou ações ao carregar a interface
        btnIncluir.setOnAction(e -> System.out.println("Incluir clicado!"));
        btnEditar.setOnAction(e -> System.out.println("Editar clicado!"));
        btnLimpar.setOnAction(e -> System.out.println("limpar textfilds!"));
        btnExcluir.setOnAction(e -> System.out.println("Excluir clicado!"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItemDaTableView(newValue));
    }

    public void carregarTableView() throws SQLException {
        // Configurando as colunas
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));

        // Inicializando a lista de alunos
        alunos = Aluno.consultarAluno(null, 2);
        obsAlunos = FXCollections.observableArrayList(alunos);
        tableView.setItems(obsAlunos);
    }

    public void selecionarItemDaTableView(Aluno aluno){
        cpf.setText(aluno.getCpf());
        nome.setText(aluno.getNome());
        dataNascimento.setText(aluno.getDataNascimento());
        peso.setText(String.valueOf(aluno.getPeso()));
        altura.setText(String.valueOf(aluno.getAltura()));
    }
}