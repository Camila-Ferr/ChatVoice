package cliente;

public class VozCliente {
    public static boolean calling = false;
    public static void main(String[] args){
        Cliente cliente = new Cliente();

        while (true) {
            System.out.println("Digite 1 para iniciar :");
            System.out.println("Digite 2 para parar :");


            cliente.init_audio();

        }

    }
}
