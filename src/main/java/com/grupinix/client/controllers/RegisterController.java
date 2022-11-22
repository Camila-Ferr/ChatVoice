package com.grupinix.client.controllers;

import com.grupinix.client.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {

    public ListView<String> NomeListView;
    public ListView<String> PortaListView;
    public ListView<String> IPListView;
    Cliente cliente;
    private final ArrayList<String> id_clientes = new ArrayList<>();
    private final ArrayList<String> id_enderecos = new ArrayList<>();
    private final ArrayList<String> id_portas = new ArrayList<>();

    public void initialize() throws IOException {
        cliente = LoginController.chatCliente;
        setList();


    }
    public void setList () throws IOException {
        cliente.clientSocket.msgSend("*atualiza");
        String enderecos = cliente.clientSocket.getMessage();


        Pattern regex = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher = regex.matcher(enderecos);

        Pattern regex2 = Pattern.compile("\\{(.*?)\\}");
        Matcher regexMatcher2 = regex2.matcher(enderecos);

        while (regexMatcher.find()){
            String nome = regexMatcher.group(1);
            id_clientes.add(nome);
        }

        while (regexMatcher2.find()){
            String end = regexMatcher2.group(1);
            id_enderecos.add(end.split(":")[0]);
            id_portas.add(end.split(":")[1]);
        }
        for (String i : id_clientes) {
            NomeListView.getItems().add(i);
        }
        for (String i: id_portas){
            PortaListView.getItems().add(i);
        }
        for (String i: id_enderecos){
            IPListView.getItems().add(i);
        }


    }
    public void changeSceneToChat(ActionEvent event) throws Exception {
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
