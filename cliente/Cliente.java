package cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private final String SERVER_ADRESS = "127.0.0.1";
    private static final int PORT_SERVIDOR = 9000;
    private static final int PORT_LIGACAO = 6000;
    private ClienteSocket clientSocket;
    private Scanner scanner;
    public static boolean calling = false;
    
    public Cliente(){
        scanner = new Scanner(System.in);
    }
    public void start() throws IOException {
        clientSocket = new ClienteSocket(new Socket(SERVER_ADRESS, PORT_SERVIDOR));
        String mensagem;
        String message = scanner.nextLine();
        clientSocket.msgSend(message);
        mensagem = clientSocket.getMessage();
        System.out.println(mensagem);
        mensage_loop();
    }

    private void mensage_loop() throws IOException{
        if (scanner.nextInt() == 1){
            Ligacao ligacao = new Ligacao(PORT_LIGACAO);
            Cliente.calling = true;
            ligacao.init_audio_receive();
            ligacao.init_audio_send();
        }
        
    }
    public static void main (String[]args) throws IOException {
        try {
            Cliente cliente = new Cliente();
            cliente.start();
        } catch (IOException ex){
            throw new IOException();
        }
    }
}
