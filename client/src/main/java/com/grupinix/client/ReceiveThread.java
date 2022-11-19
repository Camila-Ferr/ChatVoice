package com.grupinix.client;

import javax.sound.sampled.SourceDataLine;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveThread extends Thread{
    public DatagramSocket din;
    public SourceDataLine audio_out;
    byte[] buffer = new byte[512];

    @Override
    public void run(){
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        while (Application.calling){
            try {
                din.receive(incoming);
                buffer = incoming.getData();
                audio_out.write(buffer,0,buffer.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            

        }
    }
}
