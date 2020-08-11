package socket_servidor.model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender {

    DataOutputStream out;                                                       //Flujo de datos de salida

    public void send(String mensaje, Socket sc) {
        try {
            out = new DataOutputStream(sc.getOutputStream());                   //Variable de salida de datos hacia el cliente
            out.writeUTF(mensaje);                                              //Respuesta del servidor al cliente
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
