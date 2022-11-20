package com.grupinix.client.controllers;
import com.grupinix.client.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    public TextField Nickname;
    public Label nomeInvalidoLabel;
    public Button loginToCallCenterButton;

    public static Cliente chatCliente;
    public Button Conectar;

    private boolean changeScene;

    public void initialize() {
        this.changeScene = false;
        nomeInvalidoLabel.setVisible(false);
        Conectar.setVisible(false);
        changeScene = false;

    }

    public void connect() throws IOException {
        String apelido = Nickname.getText();
        chatCliente = new Cliente(apelido);

        if (chatCliente.clientSocket.getMessage().equals("-1")){
            nomeInvalidoLabel.setVisible(true);
        }
        else {
            nomeInvalidoLabel.setVisible(false);
            changeScene = true;
            Conectar.setVisible(true);
        }
    }



    public void changeSceneToChat(ActionEvent event) throws Exception {
        if (this.changeScene) {
            URL chat = getClass().getClassLoader().getResource("chat.fxml");
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
