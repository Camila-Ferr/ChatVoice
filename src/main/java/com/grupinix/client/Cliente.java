package com.grupinix.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private final String SERVER_ADRESS = "localhost";
    private static final int PORT_SERVIDOR = 9000;
    private static final int PORT_LIGACAO = 6000;
    public ClienteSocket clientSocket;
    private Scanner scanner;
    public static boolean calling = false;
    
    public Cliente(String apelido) throws IOException {
       this.start(apelido);
    }
    public void start(String apelido) throws IOException {
        String mensagem;

        clientSocket = new ClienteSocket(new Socket(SERVER_ADRESS, PORT_SERVIDOR), apelido);
        clientSocket.msgSend(apelido);
    }

    private void mensage_loop() throws IOException{
        if (scanner.nextInt() == 1){
            Ligacao ligacao = new Ligacao(PORT_LIGACAO);
            Cliente.calling = true;
            ligacao.init_audio_receive();
            ligacao.init_audio_send();
        }
        
    }
}
