package com.grupinix.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClienteSocket {
    private final Socket socket;
    private BufferedReader in;
    private final PrintWriter out;

    private final String apelido;


    public ClienteSocket(Socket socket, String apelido) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.apelido = apelido;
    }

    public boolean msgSend(String msg) throws IOException {
        out.println(msg);
        return !out.checkError();
    }
    public void limpaBuffer ()  throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public String getMessage() throws IOException {
        String mensagem = "";
        mensagem = in.readLine();
        limpaBuffer();
        return replace(mensagem);
    }

    public String replace(String mensagem){
        String nova_mensagem = mensagem.replaceAll("%", "\n");
        return nova_mensagem;
    }

    public String getApelido(){
        return this.apelido;
    }
}
