package com.example.projetoalunoimc.gui;
import com.example.projetoalunoimc.modelo.Aluno;

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
    private Button btnIMC;


    @FXML
    public void initialize() throws SQLException {
        carregarTableView();

        // Inicializações ou ações ao carregar a interface
        btnIncluir.setOnAction(e -> {
            try {
                incluirAluno();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnEditar.setOnAction(e -> {
            try {
                atualizarSelecionado();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnLimpar.setOnAction(e -> limpaTextField());

        btnExcluir.setOnAction(e -> {
            try {
                excluirSelecionado();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnIMC.setOnAction(e -> {
            try {
                calculaIMC();
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
        if (aluno != null) {
            this.aluno = aluno;
            cpf.setText(aluno.getCpf());
            nome.setText(aluno.getNome());
            dataNascimento.setText(aluno.getDataNascimento());
            peso.setText(String.valueOf(aluno.getPeso()));
            altura.setText(String.valueOf(aluno.getAltura()));
        }
    }

    public void limpaTextField() {
        cpf.setText("");
        nome.setText("");
        dataNascimento.setText("");
        peso.setText("");
        altura.setText("");
        this.aluno = null;
    }

    public void excluirSelecionado() throws SQLException {
        if (this.aluno != null) {
            Aluno.excluirAluno(this.aluno);
            acaoPadrao();
        }
    }

    public void incluirAluno() throws SQLException {
        if ((cpf.getText() != "")&&(nome.getText() != "")&&(dataNascimento.getText() != "")&&(peso.getText() != "")&&(altura.getText() != "")) {

            this.aluno = new Aluno(0, cpf.getText(), nome.getText(), dataNascimento.getText(), Float.parseFloat(peso.getText()), Float.parseFloat(altura.getText()));

            Aluno.inserirAluno(this.aluno);

            acaoPadrao();
        }
    }

    public void atualizarSelecionado() throws SQLException {
        if (this.aluno != null) {
            this.aluno.setCpf(cpf.getText());
            this.aluno.setNome(nome.getText());
            this.aluno.setDataNascimento(dataNascimento.getText());
            this.aluno.setPeso(Float.parseFloat(peso.getText()));
            this.aluno.setAltura(Float.parseFloat(altura.getText()));

            Aluno.atualizarAluno(this.aluno);

            acaoPadrao();
        }
    }

    public void calculaIMC() throws SQLException {
        if (this.aluno != null) {
            this.aluno.calculaIMC();
        }
    }

    public void acaoPadrao() throws SQLException {
        limpaTextField();
        carregarTableView();
    }

}