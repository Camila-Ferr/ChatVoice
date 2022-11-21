package com.grupinix.client.controllers;

import com.grupinix.client.controllers.ChatController;

import java.io.IOException;

public class ObserverThread extends Thread {
    private ChatController chatController;
    private boolean rerun = false;

    public synchronized void setChatController(ChatController chatController){
        this.chatController = chatController;
    }
    public synchronized ChatController getChatController(){
        return this.chatController;
    }
    public void setRerun(boolean rerun){
        this.rerun = rerun;
    }
    @Override
    public void run() {
        while (true) {
            if (!rerun){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            try {
                String mensagemLigacao = this.getChatController().cliente.clientSocket.getMessage();

                if (mensagemLigacao.contains("convite") || mensagemLigacao.contains("aceita") || mensagemLigacao.contains("rejeita")) {
                    String[] ligacao = mensagemLigacao.split("\\$");
                    System.out.println(mensagemLigacao);

                    if (ligacao[1].equals("*convite")) {
                        this.getChatController().convite(ligacao[0]);
                    } else if (ligacao[2].equals("*aceita")) {
                        this.getChatController().pesquisado(new String[]{ligacao[0]});
                        this.getChatController().setCalling(true);
                    } else if (mensagemLigacao.split("\\$")[1].equals("*rejeita")) {
                       this.getChatController().setVisibilityOfHBox(false);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){

            }
        }
    };
}
