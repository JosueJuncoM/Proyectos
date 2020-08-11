package socket_cliente.model;

import socket_cliente.view.Presentator;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Messenger {

    public DataOutputStream out;
    public Presentator ps = new Presentator();                 //Se creaun objeto de la clase Presentator

    public int cliente(Socket sc, String command, int estadoConexion) {
        int estado = 1;
        //Flujo de datos de salida
        try {
            out = new DataOutputStream(sc.getOutputStream());  //Variable de salida de datos hacia el servidor
            out.writeUTF(command);                             //Envia el mensaje al servidor y espera la respuesta
            if (estadoConexion == 1) {
                estado = ps.presentar(sc);                              //Se ejecuta el metodo presentar que solicita el objeto socket cliente
            }
        } catch (IOException ex) {
            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
}
