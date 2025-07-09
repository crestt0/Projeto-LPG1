package com.exemplo.listadeafazeresfx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleListaTarefas {
    @FXML private TextField campoNovaTarefa;
    @FXML private ListView<Tarefa> listaDeTarefasView;

    @FXML
    public void initialize() {
        listaDeTarefasView.setItems(FXCollections.observableArrayList());
        carregarTarefasDoBanco();
    }

    @FXML
    private void eventoAdicionarTarefa() {
        String descricao = campoNovaTarefa.getText().trim();
        if (!descricao.isEmpty()) {
            GerenciadorBancoDeDados.adicionarTarefa(descricao);
            campoNovaTarefa.clear();
            carregarTarefasDoBanco();
        }
    }

    @FXML
    private void eventoRemoverTarefa() {
        Tarefa tarefaSelecionada = listaDeTarefasView.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada != null) {
            GerenciadorBancoDeDados.removerTarefa(tarefaSelecionada.getId());
            carregarTarefasDoBanco();
        } else {
            new Alert(Alert.AlertType.WARNING, "Selecione uma tarefa para remover.").showAndWait();
        }
    }

    private void carregarTarefasDoBanco() {
        listaDeTarefasView.getItems().setAll(GerenciadorBancoDeDados.buscarTodasAsTarefas());
    }
}