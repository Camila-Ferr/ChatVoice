package com.grupinix.client.controllers;

import com.grupinix.client.controllers.ChatController;

import java.io.IOException;

public class ObserverThread extends Thread {
    private ChatController chatController;
    private boolean rerun = false;
    private boolean sleep = false;

    public synchronized void setChatController(ChatController chatController){
        this.chatController = chatController;
    }
    public synchronized ChatController getChatController(){
        return this.chatController;
    }
    public synchronized Boolean getRerun(){
        return this.rerun;
    }
    public  void  setSleep(Boolean sleep){
        this.sleep = sleep;

    }
    public void setRerun(boolean rerun){
        this.rerun = rerun;
    }
    @Override
    public void run() {
        while (true) {
            if (!rerun || sleep){
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

                    if (mensagemLigacao.split("\\$")[1].equals("*rejeita")) {
                        this.getChatController().setImagePhone1(false);
                        this.getChatController().setWaitImageView1(false);
                    }
                    else if (ligacao[2].equals("*convite")) {
                        this.getChatController().convite(ligacao[0]);
                        String ip = ligacao[1].split("/")[1];
                        this.getChatController().setIp(ip.split(":")[0]);
                        this.getChatController().setId(ligacao[0]);
                        Thread.sleep(100);


                    } else if (ligacao[2].equals("*aceita")) {
                        this.getChatController().aceita(ligacao[0]);
                        String ip = ligacao[1].split(":")[0];
                        this.getChatController().setIp(ip.split("/")[1]);
                        this.getChatController().setId(ligacao[0]);
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){

            }
        }
    };
}
