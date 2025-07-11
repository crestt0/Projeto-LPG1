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

        // Aumentei a altura para 600 para acomodar melhor o design
        Scene cena = new Scene(carregadorFxml.load(), 500, 600);

        //CARREGAR O CSS
        cena.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());

        palcoPrincipal.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("icone.png")));
        palcoPrincipal.setTitle("Lista de Afazeres");
        palcoPrincipal.setScene(cena);
        palcoPrincipal.setResizable(false);
        palcoPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}