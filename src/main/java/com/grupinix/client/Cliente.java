package com.grupinix.client;

import com.grupinix.client.controllers.ChatController;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends ChatController {
    private final String SERVER_ADRESS;
    private static final int PORT_SERVIDOR = 5000;
    private static final int PORT_LIGACAO = 6000;
    public ClienteSocket clientSocket;
    public static boolean calling = false;
    
    public Cliente(String apelido, String ip) throws IOException {
        this.SERVER_ADRESS = ip;
        this.start(apelido);
    }
    public void start(String apelido) throws IOException {

        clientSocket = new ClienteSocket(new Socket(SERVER_ADRESS, PORT_SERVIDOR), apelido);
        clientSocket.msgSend(apelido);
    }



    public void close(){
        Cliente.calling = false;
    }
        
}
