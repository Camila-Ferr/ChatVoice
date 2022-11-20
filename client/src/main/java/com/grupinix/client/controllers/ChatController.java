package com.grupinix.client.controllers;

import com.grupinix.client.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatController {
    public ImageView ImagePhone1;
    public ImageView ImageBlock1;
    public ImageView ImagePhone2;
    public ImageView ImageBlock2;
    public ImageView ImagePhone3;
    public ImageView ImageBlock3;
    public ImageView ImagePhone4;
    public ImageView ImageBlock4;
    public ImageView ImagePhone5;
    public ImageView ImageBlock5;
    public ImageView ImagePhone6;
    public ImageView ImageBlock6;
    public ImageView ImagePhone7;
    public ImageView ImageBlock7;

    public Label Nome1;
    public Label Nome2;
    public Label Nome3;
    public Label Nome4;
    public Label Nome5;
    public Label Nome6;
    public Label Nome7;
    public Button searchButton;
    public Button registerButton;
    public TextField TextFieldSearch;
    Cliente cliente = LoginController.chatCliente;
    //Nome da pessoa
    public Label CliNickname;
    public Label Pessoa1;
    public Label Pessoa2;
    public Label Pessoa3;
    public Label Pessoa4;
    public Label Pessoa5;
    public Label Pessoa6;
    public Label Pessoa7;
    private final ArrayList<String> id_clientes = new ArrayList<>();
    private final ArrayList<Label> labels_id = new ArrayList<>();
    private final ArrayList<ImageView> button_fone = new ArrayList<>();
    private final ArrayList<String> em_ligacao = new ArrayList<>();
    private final ArrayList<ImageView> button_block = new ArrayList<>();
    private final ArrayList<Label> nome_label = new ArrayList<>();


    private String pessoas;
    private String ligacao;

    public void initialize() throws IOException {
        CliNickname.setText(cliente.clientSocket.getApelido());
        searchButton.setVisible(true);

        labels_id.add(Pessoa1);
        labels_id.add(Pessoa2);
        labels_id.add(Pessoa3);
        labels_id.add(Pessoa4);
        labels_id.add(Pessoa5);
        labels_id.add(Pessoa6);
        labels_id.add(Pessoa7);


        button_fone.add(ImagePhone1);
        button_fone.add(ImagePhone2);
        button_fone.add(ImagePhone3);
        button_fone.add(ImagePhone4);
        button_fone.add(ImagePhone5);
        button_fone.add(ImagePhone6);
        button_fone.add(ImagePhone7);


        button_block.add(ImageBlock1);
        button_block.add(ImageBlock2);
        button_block.add(ImageBlock3);
        button_block.add(ImageBlock4);
        button_block.add(ImageBlock5);
        button_block.add(ImageBlock6);
        button_block.add(ImageBlock7);


        nome_label.add(Nome1);
        nome_label.add(Nome2);
        nome_label.add(Nome3);
        nome_label.add(Nome4);
        nome_label.add(Nome5);
        nome_label.add(Nome6);
        nome_label.add(Nome7);
        setFalse();
        setPessoas();


    }
    public void setPessoas () throws IOException {
        int indice = 0;
        cliente.clientSocket.msgSend("*atualiza");
        pessoas = cliente.clientSocket.getMessage();
        cliente.clientSocket.msgSend("*ligacao");
        ligacao = cliente.clientSocket.getMessage();


        Pattern regex = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher = regex.matcher(pessoas);

        Pattern regex2 = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher2 = regex2.matcher(ligacao);

        while (regexMatcher2.find()){
            em_ligacao.add(regexMatcher2.group(1));
        }
        while (regexMatcher.find()) {
            String nome = regexMatcher.group(1);
            id_clientes.add(nome);
            labels_id.get(indice).setText(id_clientes.get(indice));

            if (em_ligacao.get(indice).equals("true")){
                button_block.get(indice).setVisible(true);
            }
            else {
                button_fone.get(indice).setVisible(true);
            }

            labels_id.get(indice).setVisible(true);
            nome_label.get(indice).setVisible(true);

            indice = indice +1;
        }
    }
    public void setFalse(){
        for (Label pessoa: labels_id){
            pessoa.setVisible(false);
        }
        for (Label nome : nome_label){
            nome.setVisible(false);
        }
        for (ImageView button_fone : button_fone){
            button_fone.setVisible(false);
        }
        for (ImageView button_block : button_block){
            button_block.setVisible(false);
        }
    }

    public void pesquisa() throws IOException, InterruptedException {

        cliente.clientSocket.msgSend("*acha");
        String procura = TextFieldSearch.getText();
        cliente.clientSocket.msgSend(procura);
        setFalse();
        String achado = cliente.clientSocket.getMessage();
        String [] nome_achado = achado.split(" ");

        if (!nome_achado[0].equals("()")){
            for (int i = 0; i<id_clientes.size(); i++){

                if (nome_achado[0].equals(id_clientes.get(i).split(" ")[1])){
                    nome_label.get(i).setVisible(true);
                    labels_id.get(i).setVisible(true);

                    if (em_ligacao.get(i).equals("false")){
                        button_fone.get(i).setVisible(true);
                    }
                    else {
                        button_block.get(i).setVisible(true);
                    }
                }
            }

        }


    }
    public void changeSceneToRegister(ActionEvent event) throws Exception {
        URL register = getClass().getClassLoader().getResource("register.fxml");
        if (register == null) return;

        Parent chatParent = FXMLLoader.load(register);
        Scene registerScene = new Scene(chatParent);

        // Pega a informção da cena
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(registerScene);
        window.show();
    }

    public void changeSceneToVirtualCall(ActionEvent event) throws Exception {
        URL virtualCall = getClass().getClassLoader().getResource("virtualCall.fxml");
        if (virtualCall == null) return;

        Parent chatParent = FXMLLoader.load(virtualCall);
        Scene registerScene = new Scene(chatParent);

        // Pega a informção da cena
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(registerScene);
        window.show();
    }


}
