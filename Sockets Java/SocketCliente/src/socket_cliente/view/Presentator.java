package socket_cliente.view;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Presentator {

    DataInputStream in;                                     //Flujo de datos de entrada

    public int presentar(Socket sc) {
        int estado = 1;
        try {
            in = new DataInputStream(sc.getInputStream());  //Datos recibidos por el servidor
            String mensaje = in.readUTF();                  //Se almacena la respuesta del servidor
            String[] parts = mensaje.split("-");    //Se separan los dos numeros y se almacenan en parts[]
            if (parts.length == 2) {
                if (parts[0].equals("0")) {
                    estado = 0;
                    sc.close();
                    System.out.println(parts[1]);                    //Se imprime en pantalla el resultado     
                } else {
                    System.out.println(parts[1]);                    //Se imprime en pantalla el resultado  
                }
            } else {
                System.out.println(mensaje);                    //Se imprime en pantalla el resultado     
            }

        } catch (IOException ex) {
            Logger.getLogger(Presentator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
}
