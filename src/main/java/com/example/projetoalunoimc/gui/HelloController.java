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

    private Aluno aluno = null;

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
        btnLimpar.setOnAction(e -> limpaTextField());
        btnExcluir.setOnAction(e -> {
            try {
                excluirSelecionado();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItemDaTableView(newValue));
    }

    public void carregarTableView() throws SQLException {
        tableView.getItems().clear();

        ObservableList<Aluno> obsAlunos;
        List<Aluno> alunos = new ArrayList<>();

        // Configurando as colunas
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));

        // Inicializando a lista de alunos
        alunos = Aluno.consultarAluno(null);
        obsAlunos = FXCollections.observableArrayList(alunos);
        tableView.setItems(obsAlunos);
    }

    public void selecionarItemDaTableView(Aluno aluno){
        this.aluno = aluno;
        cpf.setText(aluno.getCpf());
        nome.setText(aluno.getNome());
        dataNascimento.setText(aluno.getDataNascimento());
        peso.setText(String.valueOf(aluno.getPeso()));
        altura.setText(String.valueOf(aluno.getAltura()));
    }

    public void limpaTextField() {
        cpf.setText("");
        nome.setText("");
        dataNascimento.setText("");
        peso.setText("");
        altura.setText("");
    }

    public void excluirSelecionado() throws SQLException {
        if (this.aluno != null) {
            Aluno.excluirAluno(this.aluno);
            acaoPadrao();
        }
    }

    public void acaoPadrao() throws SQLException {
        limpaTextField();
        carregarTableView();
    }

}