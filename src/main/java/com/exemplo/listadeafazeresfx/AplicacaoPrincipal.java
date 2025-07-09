package com.exemplo.listadeafazeresfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AplicacaoPrincipal extends Application {
    @Override
    public void start(Stage palcoPrincipal) throws IOException {
        GerenciadorBancoDeDados.inicializarBancoDeDados();
        FXMLLoader carregadorFxml = new FXMLLoader(AplicacaoPrincipal.class.getResource("main-view.fxml"));
        Scene cena = new Scene(carregadorFxml.load(), 500, 400);
        palcoPrincipal.setTitle("Lista de Afazeres");
        palcoPrincipal.setScene(cena);
        palcoPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}