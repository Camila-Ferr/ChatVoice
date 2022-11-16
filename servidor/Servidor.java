package servidor;

import javax.sound.sampled.*;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public int porta = 8000;
    public SourceDataLine audio_out;

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
        DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format);
        if (!AudioSystem.isLineSupported(info_out)){
            System.out.println("Not supported");
            System.exit(0);
        }
        try {
            audio_out = (SourceDataLine)AudioSystem.getLine(info_out);
            audio_out.open(format);
            audio_out.start();
            ServerThread p = new ServerThread();
            p.din = new DatagramSocket(porta);
            p.audio_out = audio_out;
            Server_voice.calling = true;
            p.start();
        } catch (LineUnavailableException | SocketException e) {
            throw new RuntimeException(e);
        }

    }

}
