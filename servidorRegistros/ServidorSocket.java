package servidoRegistros;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class ServidorSocket {
    private final Socket socket;
    private BufferedReader in;
    private final PrintWriter out;
    private String client_id = null;

    public ServidorSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(),true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public boolean sendMessage(String msg) throws IOException {
        out.println(msg);
        return !out.checkError();
    }
    public void limpaBuffer ()  throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String getMessage() throws IOException {
        String msg = " ";
        msg = in.readLine();
        limpaBuffer();
        return msg;
    }

    public SocketAddress getRemoteSocketAdress() {
        return socket.getRemoteSocketAddress();
    }
    public String getClient_id(){
        return client_id;
    }
    public void setClient_id(String client_id) {
        this.client_id = client_id;
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