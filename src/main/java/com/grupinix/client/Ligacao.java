package com.grupinix.client;

import javax.sound.sampled.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ligacao{

    public int porta ;
    public String enderecoIp ;
    public TargetDataLine audio_in;
    public SourceDataLine audio_out;

    Ligacao (int porta){
        this.porta = porta;
        this.enderecoIp = "localhost";
    }

    public static AudioFormat getaudioformat(){
        float sampleRate = 8000.0F;
        int sampleSizeInbits = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate,sampleSizeInbits,channel,signed,bigEndian);
    }


    public void init_audio_send(){
        AudioFormat format = getaudioformat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)){
            System.out.println("Um erro ocorreu ao tentar enviar o áudio");
            System.exit(0);
        }
        try {
            audio_in = (TargetDataLine) AudioSystem.getLine(info);
            audio_in.open(format);
            audio_in.start();
            SendThread thread = new SendThread();
            InetAddress inet = InetAddress.getByName(enderecoIp);
            thread.audio_in = audio_in;
            thread.dout = new DatagramSocket();
            thread.server_ip = inet;
            thread.server_port = porta;
            thread.start();
        } catch (LineUnavailableException | UnknownHostException | SocketException e) {
            Logger.getLogger(Ligacao.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public void init_audio_receive(){
        AudioFormat format = getaudioformat();
        DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format);
        if (!AudioSystem.isLineSupported(info_out)){
            System.out.println("Not supported");
            System.exit(0);
        }
        try {
            audio_out = (SourceDataLine)AudioSystem.getLine(info_out);
            audio_out.open(format);
            audio_out.start();
            ReceiveThread p = new ReceiveThread();
            p.din = new DatagramSocket(porta);
            p.audio_out = audio_out;
            p.start();
        } catch (LineUnavailableException | SocketException e) {
            throw new RuntimeException(e);
        }

    }
}