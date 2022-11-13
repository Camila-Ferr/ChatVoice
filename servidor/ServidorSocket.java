package servidor;

import java.io.*;
import java.net.Socket;

public class ServidorSocket {
    private final Socket socket;
    private BufferedReader in;


    public ServidorSocket(Socket socket) throws IOException {
        this.socket = socket;

    }

    public void closeS() throws IOException {
        try {
            in.close();
            socket.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
