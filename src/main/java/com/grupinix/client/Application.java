package com.grupinix.client;

import com.grupinix.client.controllers.ObserverThread;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Application extends javafx.application.Application {
    public static ObserverThread observerThread;
    public static Message_loop message_loop;

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
        observerThread = new ObserverThread();
        observerThread.start();
        message_loop = new Message_loop();
        launch(args);
    }
}