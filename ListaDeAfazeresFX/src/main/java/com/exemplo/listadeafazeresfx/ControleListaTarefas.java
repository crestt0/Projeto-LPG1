package com.exemplo.listadeafazeresfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleListaTarefas {

    //Componentes do FXML
    @FXML private TextField campoNovaTarefa;
    @FXML private ListView<Tarefa> listaDeTarefasView;
    @FXML private Label labelTarefasRestantes;
    @FXML private Button botaoAdicionar;

    //Inicializa o banco e configura as cedulas
    @FXML
    public void initialize() {
        // Conecta a ListView com a nossa célula customizada, passando uma referência deste controller
        listaDeTarefasView.setCellFactory(param -> new CelulaTarefa(this));
        botaoAdicionar.setText("+");
        botaoAdicionar.setGraphic(null); // Garante que não há nenhum ícone gráfico
        carregarTarefasDoBanco();
    }

    @FXML
    private void eventoAdicionarTarefa() {
        String descricao = campoNovaTarefa.getText().trim();
        if (!descricao.isEmpty()) {
            // Adiciona a nova tarefa no banco (ela será "não concluída" por padrão)
            GerenciadorBancoDeDados.adicionarTarefa(descricao);
            campoNovaTarefa.clear();
            carregarTarefasDoBanco(); // Recarrega a lista para mostrar o novo item
        }
    }

    //Atualiza a interface e é público para que a CelulaTarefa possa chamá-lo após uma exclusão.
    public void carregarTarefasDoBanco() {
        listaDeTarefasView.getItems().setAll(GerenciadorBancoDeDados.buscarTodasAsTarefas());
        atualizarContadorTarefas(); // Sempre atualiza o contador quando a lista é recarregada
    }

    public void atualizarContadorTarefas() {
        // Conta quantas tarefas na lista NÃO estão concluídas
        long tarefasRestantes = listaDeTarefasView.getItems().stream().filter(t -> !t.isConcluida()).count();

        if (tarefasRestantes == 1) {
            labelTarefasRestantes.setText("1 tarefa restante");
        } else {
            labelTarefasRestantes.setText(tarefasRestantes + " tarefas restantes");
        }
    }
}