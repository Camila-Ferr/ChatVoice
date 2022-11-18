package servidorRegistros;

import java.io.*;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;


public class Servidor {
    private static final int PORT_SERVIDOR = 9000;
    private ServerSocket serverSocket;
    private final List<ServidorSocket> clients = new LinkedList<>();


    public void start() throws IOException {
        System.out.println("Servidor iniciado na porta " + PORT_SERVIDOR);

        serverSocket = new ServerSocket(PORT_SERVIDOR);
        new Thread(() -> {
            try {
                clienteConnectionLoop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void clienteConnectionLoop() throws IOException {
        while (true) {

            ServidorSocket cliente = new ServidorSocket(serverSocket.accept());
            cliente.setClient_id(cliente.getMessage());
            System.out.println(" Cliente : "+cliente.getClient_id());
            clients.add(cliente);
            mostra_online(cliente);
            
            try { 
                new Thread(() -> {
                    try {
                        envia_online(cliente);
                        clientMessageLoop(cliente);
                    } catch (Exception e) {
                        clients.remove(cliente);
                    }
                }).start();
                } catch (Exception e) {

                    try {
                        cliente.closeS();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    clients.remove(cliente);
                    throw new RuntimeException(e);
                }
        }
    }
    public void envia_online(ServidorSocket cliente_principal) throws IOException{
        String mensagem = "";
        for( ServidorSocket cliente : clients){
            mensagem = mensagem.concat(cliente.getClient_id().concat(cliente.getRemoteSocketAdress().toString()));
            
        }
        System.out.println(mensagem);
        cliente_principal.sendMessage(mensagem);
    }
    public void mostra_online(ServidorSocket cliente_principal) throws IOException{
        System.out.println("Lista de clientes online :");
        System.out.println("");
        for( ServidorSocket cliente : clients){
            System.out.println(cliente.getClient_id());
            System.out.println(cliente.getRemoteSocketAdress());
            System.out.println("");
            
        }
    }

     private void clientMessageLoop(ServidorSocket socket) throws IOException {
        String message = null;
        try {
            while (true) {
                message = socket.getMessage();
                if ("*exit".equals(message)) {
                    socket.closeS();
                    clients.remove(socket);
                    return;
                } 
                System.out.printf("Cliente: %s\n", socket.getRemoteSocketAdress());
                System.out.printf("Mensagem: %s\n", message);
            }
        } catch (IOException e) {
            socket.sendMessage("");
            socket.closeS();
            clients.remove(socket);
        }
    }

    public static void main(String[] args) throws IOException {
        //Serviço de escuta

        try {
            Servidor servidor = new Servidor();
            servidor.start();

        } catch (IOException e) {
            throw new IOException();
        }

    }
}