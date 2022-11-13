package servidor;


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
            clients.add(cliente);

            new Thread(() -> {
                System.out.println("funfei");
        });}
    }

    public static void main(String[] args) throws Exception {
        //Serviço de escuta

        try {
            Servidor servidor = new Servidor();
            servidor.start();

        } catch (IOException e) {
            throw new Exception();
        }

    }
}
