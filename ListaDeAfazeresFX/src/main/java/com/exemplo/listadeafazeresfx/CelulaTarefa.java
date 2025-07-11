package com.exemplo.listadeafazeresfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class CelulaTarefa extends ListCell<Tarefa> {

    @FXML private HBox hBoxContainer;
    @FXML private CheckBox checkboxConcluida;
    @FXML private Text labelDescricao;
    @FXML private Button botaoRemover;

    private FXMLLoader fxmlLoader;
    private final ControleListaTarefas controlePrincipal;

    public CelulaTarefa(ControleListaTarefas controlePrincipal) {
        this.controlePrincipal = controlePrincipal;
    }

    @Override
    protected void updateItem(Tarefa tarefa, boolean empty) {
        super.updateItem(tarefa, empty);

        if (empty || tarefa == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("celula_tarefa.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            labelDescricao.setText(tarefa.getDescricao());
            checkboxConcluida.setSelected(tarefa.isConcluida());
            botaoRemover.setText("x");

            // Marcar Concluido
            aplicarEstiloConcluido(tarefa.isConcluida());

            configurarAcoes(tarefa);
            setText(null);
            setGraphic(hBoxContainer);
        }
    }

    private void configurarAcoes(Tarefa tarefa) {
        botaoRemover.setOnAction(event -> {
            GerenciadorBancoDeDados.removerTarefa(tarefa.getId());
            controlePrincipal.carregarTarefasDoBanco();
        });

        checkboxConcluida.setOnAction(event -> {
            boolean novoStatus = checkboxConcluida.isSelected();
            tarefa.setConcluida(novoStatus);
            GerenciadorBancoDeDados.atualizarStatusTarefa(tarefa.getId(), novoStatus);
            aplicarEstiloConcluido(novoStatus);
            controlePrincipal.atualizarContadorTarefas();
        });
    }

    //Marcar Concluido Label risquinho
    private void aplicarEstiloConcluido(boolean concluido) {
        labelDescricao.setStrikethrough(concluido);
        labelDescricao.setFill(concluido ? Color.web("#ADB5BD") : Color.web("#333333"));
    }

}