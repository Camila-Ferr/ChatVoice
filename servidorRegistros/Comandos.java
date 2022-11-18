package servidorRegistros;

import java.io.IOException;
import java.util.ArrayList;

public class Comandos {
    public static void envia_online(ServidorSocket cliente_principal,ArrayList<ServidorSocket> clients) throws IOException{
        String mensagem = "";
        for( ServidorSocket cliente : clients){
            mensagem = mensagem.concat(cliente.getClient_id().concat("    ").concat(cliente.getRemoteSocketAdress().toString())).concat(" % ");
            
        }
        cliente_principal.sendMessage(mensagem);
    }
    public static void mostra_online(ServidorSocket cliente_principal,ArrayList<ServidorSocket> clients ) throws IOException{
        System.out.println("Lista de clientes online :");
        System.out.println("");
        for( ServidorSocket cliente : clients){
            System.out.println(cliente.getClient_id());
            System.out.println(cliente.getRemoteSocketAdress());
            System.out.println("");
            
        }
    }
    public static ServidorSocket procura_cliente (ArrayList<ServidorSocket> clients, String idCliente){
        
        ServidorSocket clienteprocurado = null;
        boolean achou = false;

        for( ServidorSocket cliente : clients){
           if (cliente.getClient_id().equals(idCliente)){
            achou = true;
            clienteprocurado = cliente;
            break;
           }
            
        }
        return (achou ? clienteprocurado : null); 
    }

    public static boolean valida_nome (ArrayList<ServidorSocket> clients, String idCliente){
        
        boolean achou = false;

        for( ServidorSocket cliente : clients){
            System.out.println(idCliente);
           if (cliente.getClient_id().equals(idCliente)){
            achou = true;
            break;
           }
            
        }
        return (!achou);
    }


}
