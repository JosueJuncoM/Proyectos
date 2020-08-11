/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket_servidor.model.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import socket_servidor.controller.ReadComServer;

/**
 *
 * @author Gigabyte
 */
public class Process extends Thread {

    public int PUERTO;
    public Socket sc;

    public Process(String name, int Puerto, Socket cliente) {
        super(name);
        this.PUERTO = Puerto;
        this.sc = cliente;
    }

    @Override
    public void run() {
        int estadoHilo = 1;
        while (estadoHilo == 1) {
            try {
                ServerSocket servidor = null;     //Socket Servidor
                DataInputStream in;               //Flujo de datos de entrada
                servidor = new ServerSocket(PUERTO);

                //Servidor espera la peticion del cliente
                System.out.println("Cliente " + getName() + " - Conectado");

                int est = 1;
                while (est == 1) {
                    in = new DataInputStream(sc.getInputStream());              //Datos recibidos por el cliente
                    String mensaje = in.readUTF();                              //Se almacenan los datos recibidos
                    //analizar el comando
                    ReadComServer rcs = new ReadComServer();
                    est = rcs.readCommand(mensaje, sc, getName());
                }
                estadoHilo = 0;
                servidor.close();
            } catch (IOException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
