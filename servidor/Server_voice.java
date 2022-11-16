package servidor;

public class Server_voice {
    public static boolean calling = false;
    public static void main(String[] args){
        Servidor servidor = new Servidor();
        servidor.init_audio();
    }
}
