package cliente;

import javax.sound.sampled.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends javax.swing.JFrame{

    public static final int PORT_SERVER = 8000;
    public static final String ADD_SERVER = "127.0.0.1";
    TargetDataLine audio_in;

    public static AudioFormat getaudioformat(){
        float sampleRate = 8000.0F;
        int sampleSizeInbits = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate,sampleSizeInbits,channel,signed,bigEndian);
    }


    public void init_audio(){
        AudioFormat format = getaudioformat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)){
            System.out.println("Merda");
            System.exit(0);
        }
        try {
            audio_in = (TargetDataLine) AudioSystem.getLine(info);
            audio_in.open(format);
            audio_in.start();
            GravaçaoThread thread = new GravaçaoThread();
            InetAddress inet = InetAddress.getByName(ADD_SERVER);
            thread.audio_in = audio_in;
            thread.dout = new DatagramSocket();
            thread.server_ip = inet;
            thread.server_port = PORT_SERVER;
            VozCliente.calling = true;
            thread.start();
        } catch (LineUnavailableException | UnknownHostException | SocketException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}