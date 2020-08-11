package socket_servidor.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import socket_servidor.model.Sender;
import socket_servidor.model.thread.Places;

public class Receiver {

    public static void main(String[] args) {
        Places pl = new Places();
        ServerSocket servidor = null;     //Socket Servidor
        Socket sc = null;                 //Socket cliente
        final int PUERTO = 1222;          //Variable constante: Puerto del socket
        DataInputStream in;               //Flujo de datos de entrada

        try {
            servidor = new ServerSocket(PUERTO);                                //Creacion del socket servidor con el puerto
            System.out.println("----------Servidor Iniciado----------");
            pl.setPlace(1);
            pl.setIni(0);
            while (true) {

                //Servidor espera la peticion del cliente
                sc = servidor.accept();                                         //En espera

                //Cliente hilos
                if (pl.getIni() == 0) {
                    if (pl.getPlace() < 6) {
                        pl.con(PUERTO, sc);
                    }
                } else {
                    //verifica si hay lugares disponibles en el servidor
                    if (pl.verEstadohilos() == true) {
                        pl.con(PUERTO, sc);
                    } else {
                        //si no hay sito disponible envia un mensaje al cliente
                        Sender se = new Sender();
                        se.send("0-Todos los clientes ocupados.", sc);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
