package com.grupinix.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class LoginController {

    public void initialize() {
        this.ChangeScene = false;

    }
    private boolean ChangeScene;

    public void changeSceneToChat(ActionEvent event) throws Exception {
        if (this.ChangeScene) {
            URL chat = getClass().getResource("/views/SceneChat.fxml");
            if (chat == null) return;

            Parent chatParent = FXMLLoader.load(chat);
            Scene chatScene = new Scene(chatParent);

            // Pega a informção da cena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(chatScene);
            window.show();
        }
    }
}
