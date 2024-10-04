package com.example.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp extends Application {
    private ArrayList<String> livros = new ArrayList<>(); // Lista para armazenar livros

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Digite o nome do livro:");
        TextField textField = new TextField();
        Button btn = new Button("Adicionar Livro");
        ListView<String> listView = new ListView<>(); // Lista para exibir livros

        btn.setOnAction(e -> {
            String livro = textField.getText();
            if (!livro.isEmpty()) {
                livros.add(livro); // Adiciona o livro à lista
                listView.getItems().add(livro); // Atualiza a ListView
                textField.clear(); // Limpa o campo de texto
                System.out.println("Livro adicionado: " + livro);
            }
        });

        VBox root = new VBox(10); // VBox com espaçamento de 10
        root.getChildren().addAll(label, textField, btn, listView); // Adiciona todos os componentes
        primaryStage.setScene(new Scene(root, 400, 300)); // Aumenta a largura da janela
        primaryStage.setTitle("Gerenciamento de Biblioteca");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
