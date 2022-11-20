package com.grupinix.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL login = this.getClass().getClassLoader().getResource("login.fxml");
        if (login == null) return;

        Parent root = FXMLLoader.load(login);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.setWidth(1026);
        stage.setHeight(800);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}