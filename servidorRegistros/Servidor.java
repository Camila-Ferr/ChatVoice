package servidorRegistros;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;


public class Servidor {
    private static final int PORT_SERVIDOR = 9000;
    private ServerSocket serverSocket;
    private final ArrayList<ServidorSocket> clients = new ArrayList<>();


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
            boolean prossegue = Comandos.valida_nome(clients, cliente.getClient_id());
            
            if (prossegue){
                System.out.println(" Cliente : "+cliente.getClient_id());
                clients.add(cliente);
                Comandos.mostra_online(cliente,clients);
            
                try { 
                    new Thread(() -> {
                        try {
                            Comandos.envia_online(cliente,clients);
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
    }

     private void clientMessageLoop(ServidorSocket socket) throws IOException {
        String message = null;
        try {
            while (true) {
                message = socket.getMessage();
                if (!message.equals(null)){
                    if ("*exit".equals(message)) {
                        socket.closeS();
                        clients.remove(socket);
                        return;
                    }
                    else if ("*atualiza".equals(message)){
                        Comandos.mostra_online(socket, clients);
                    }
                    else if ("*acha".equals(message)){
                        String nome = socket.getMessage();
                        Comandos.procura_cliente(clients, nome);
                    }

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