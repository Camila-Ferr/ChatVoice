package com.grupinix.client;

public class Message_loop extends Thread{
    public void run(String Ip){
        Ligacao ligacao = new Ligacao(Ip);
        Cliente.calling = true;
        ligacao.init_audio_receive();
        ligacao.init_audio_send();
    }

}
