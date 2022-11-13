package cliente;

import java.util.Scanner;

public class VozCliente {
    public static boolean calling = false;
    public static void main(String[] args){

        Scanner teclado = new Scanner(System.in);
        Cliente cliente = new Cliente();

        while (true) {
            System.out.println("Digite 1 para iniciar :");
            System.out.println("Digite 2 para parar :");
            int opcao = teclado.nextInt();

            if (opcao == 1) {
                cliente.init_audio();
            }
            else {
                calling = false;
            }

        }

    }
}
