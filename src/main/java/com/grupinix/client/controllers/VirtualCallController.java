package com.grupinix.client.controllers;

import com.grupinix.client.Application;
import com.grupinix.client.Cliente;
import com.grupinix.client.Message_loop;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class VirtualCallController {
    public Button endCallButton;
    public Label nomeChamada2;
    public Label nomeChamada1;
    Cliente cliente = LoginController.chatCliente;

    public void initialize() throws IOException {
        setNomesChamada();
        System.out.println(ChatController.getIp());
        Application.message_loop.run(ChatController.getIp());

    }

    public void setNomesChamada(){
        nomeChamada1.setText(ChatController.getId());
        nomeChamada2.setText(cliente.clientSocket.getApelido());

    }
    public void close(){
        cliente.close();
    }

}
