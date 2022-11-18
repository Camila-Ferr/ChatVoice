package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClienteSocket {
    private final Socket socket;
    private BufferedReader in;
    private final PrintWriter out;


    public ClienteSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public boolean msgSend(String msg) throws IOException {
        out.println(msg);
        return !out.checkError();
    }
    public void limpaBuffer ()  throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public String getMessage() throws IOException {
        String mensagem = "";
        mensagem = in.readLine();
        limpaBuffer();
        return mensagem;
    }
}
