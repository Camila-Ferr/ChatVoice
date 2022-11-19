package com.grupinix.client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;


public class Application extends javafx.application.Application {
    private final String SERVER_ADRESS = "127.0.0.1";
    private static final int PORT_SERVIDOR = 9000;
    private ClienteSocket clientSocket;
    private Scanner scanner;
    public static boolean calling = false;
    
    public Application(){
        scanner = new Scanner(System.in);
    }
    public void start(Stage stage) throws IOException {

        URL login = this.getClass().getClassLoader().getResource("login.fxml");
        if (login == null) {
            System.out.println("Sem Recurso, eu acho");
            return;
        }

        Parent root = FXMLLoader.load(login);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.setWidth(1026);
        stage.setHeight(810);
        stage.show();

        clientSocket = new ClienteSocket(new Socket(SERVER_ADRESS, PORT_SERVIDOR));
        String mensagem;
        String message = scanner.nextLine();
        clientSocket.msgSend(message);
        mensagem = clientSocket.getMessage();
        System.out.println(mensagem);
        mensage_loop();
    }

    private void mensage_loop() {
        if (scanner.nextInt() == 1){
            Ligacao ligacao = new Ligacao(scanner.nextInt());
            Application.calling = true;
            ligacao.init_audio_receive();
            ligacao.init_audio_send();
        }
    }

    public static void main (String[]args) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            Application cliente = new Application();
            try {
                cliente.start(stage);
            }
            catch (IOException ex) {
                System.out.println("Yuri salvou a gente, AMÉM!");
            }
        });
    }
}
