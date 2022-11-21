package com.grupinix.client.controllers;

import com.grupinix.client.Application;
import com.grupinix.client.Cliente;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatController  {
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
    public ImageView waitImageView1;
    public ImageView waitImageView2;
    public ImageView waitImageView3;
    public ImageView waitImageView4;
    public ImageView waitImageView5;
    public ImageView waitImageView6;
    public ImageView waitImageView7;
    public HBox callHbox;
    public Label nomeCall;
    public Button virtualCallButton1;

    protected Cliente cliente = LoginController.chatCliente;
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
    private final ArrayList<ImageView> waiting = new ArrayList<>();
    private final ArrayList<Label> nome_label = new ArrayList<>();

    private String pessoas;
    private String ligacao;
    private AtomicBoolean calling = new AtomicBoolean(cliente.calling);


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

        waiting.add(waitImageView1);
        waiting.add(waitImageView2);
        waiting.add(waitImageView3);
        waiting.add(waitImageView4);
        waiting.add(waitImageView5);
        waiting.add(waitImageView6);
        waiting.add(waitImageView7);

        callHbox.setVisible(false);
        setFalse();
        setPessoas();

        Application.observerThread.setChatController(this);
        Application.observerThread.setRerun(true);

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
            if (indice <7) {
                labels_id.get(indice).setText(id_clientes.get(indice));

                if (em_ligacao.get(indice).equals("true")) {
                    button_block.get(indice).setVisible(true);
                } else {
                    button_fone.get(indice).setVisible(true);
                }

                labels_id.get(indice).setVisible(true);
                nome_label.get(indice).setVisible(true);
            }
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
        for (ImageView wait: waiting){
            wait.setVisible(false);
        }
    }

    public void pesquisa() throws IOException, InterruptedException {

        cliente.clientSocket.msgSend("*acha");
        String procura = TextFieldSearch.getText();
        cliente.clientSocket.msgSend(procura);
        setFalse();
        String achado = cliente.clientSocket.getMessage();
        String [] nome_achado = achado.split(" ");
        pesquisado(nome_achado);


    }
    public synchronized void pesquisado(String[] nome_achado){
        Platform.runLater(() -> {
            if (!nome_achado[0].equals("()")) {
                for (int i = 0; i < id_clientes.size(); i++) {

                    if (nome_achado[0].equals(id_clientes.get(i).split(" ")[1])) {
                        Pessoa1.setText(id_clientes.get(i));
                        nome_label.get(0).setVisible(true);
                        labels_id.get(0).setVisible(true);

                        if (em_ligacao.get(0).equals("false")) {
                            button_fone.get(0).setVisible(true);
                        } else {
                            button_block.get(0).setVisible(true);
                        }
                    }
                }
            }
        });
    }

    public synchronized void convite(String from){
        Platform.runLater(() ->{
            nomeCall.setText(from);
            callHbox.setVisible(true);
        });
    }

    public void changeSceneToRegister(ActionEvent event) throws Exception {
        changeSceneToX("register.fxml", event);
    }

    public void retorna(ActionEvent event){
        try {
            setPessoas();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void virtualCallButton1(ActionEvent event) throws Exception {
        options(event,0);
    }
    public void virtualCallButton2(ActionEvent event) throws Exception {
        options(event,1);
    }
    public void virtualCallButton3(ActionEvent event) throws Exception {
        options(event,2);
    }
    public void virtualCallButton4(ActionEvent event) throws Exception {
        options(event,3);
    }
    public void virtualCallButton5(ActionEvent event) throws Exception {
        options(event,4);
    }
    public void virtualCallButton6(ActionEvent event) throws Exception {
        options(event,5);
    }
    public void virtualCallButton7(ActionEvent event) throws Exception {
        options(event,6);
    }
    public void options(ActionEvent event, int i) throws Exception {

        if (getCalling()) {
            changeSceneToVirtualCall(event);
        }
        else {
            cliente.clientSocket.msgSend("*inicia_ligacao");
            cliente.clientSocket.msgSend(id_clientes.get(i).split(" ")[1]);
            waiting.get(i).setVisible(true);
            button_fone.get(i).setVisible(false);
        }
    }

    public synchronized boolean getCalling(){
        return calling.get();
    }
    public synchronized void setCalling(boolean call){
        this.calling.set(call);
    }
    public synchronized void setVisibilityOfHBox(boolean call){
        Platform.runLater(() -> {
            this.callHbox.setVisible(call);
        });
    }


    public void changeSceneToVirtualCall(ActionEvent event) throws Exception {
        changeSceneToX("virtualCall.fxml",event);
    }
    private void changeSceneToX(String x, ActionEvent event) throws Exception {
        URL virtualCall = getClass().getClassLoader().getResource(x);
        if (virtualCall == null) return;

        Parent chatParent = FXMLLoader.load(virtualCall);
        Scene registerScene = new Scene(chatParent);

        // Pega a informção da cena
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(registerScene);
        window.show();
    }

}


