package com.grupinix.servidor;

import java.io.IOException;
import java.util.ArrayList;

public class Comandos {
    public static void envia_online(ServidorSocket cliente_principal,ArrayList<ServidorSocket> clients) throws IOException{
        String mensagem = "";
        for( ServidorSocket cliente : clients){
            if (cliente_principal.getClient_id() != cliente.getClient_id()){
                mensagem = mensagem.concat("( ").concat(cliente.getClient_id()).concat(" ) ").concat("{").concat(cliente.getRemoteSocketAdress().toString()).concat("}").concat(" %");
            }
        }
        System.out.println(mensagem);
        cliente_principal.sendMessage(mensagem);
    }

    public static void em_ligacao(ServidorSocket cliente_principal,ArrayList<ServidorSocket> clients) throws IOException{
        String mensagem = "";
        for( ServidorSocket cliente : clients){
            if (cliente_principal.getClient_id() != cliente.getClient_id()){
                mensagem = mensagem.concat(cliente.getClient_id()).concat("(").concat(cliente.getLigacao().toString()).concat(")").concat(" %");
            }
        }
        System.out.println(mensagem);
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
    public static void resposta (ServidorSocket cliente, boolean validou ) throws IOException{
        if (!validou){
            cliente.sendMessage("-1");
        }
        else{
            cliente.sendMessage("200");
        }
        
    }
    


}
