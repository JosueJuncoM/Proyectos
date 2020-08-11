package socket_servidor.model;

import socket_servidor.model.Colebrook;
import socket_servidor.model.Calculator;
import socket_servidor.model.Aplication;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Commands {

    public Sender se = new Sender();

    public int start(Socket sc) {
        se.send("Conectado correctamente", sc);
        return 1;
    }

    public int desconectar(Socket sc, String name) {
        try {
            sc.close();                                        //Cierra la conexion con el cliente
            System.out.println("Cliente " + name + " - Desconectado");
        } catch (IOException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int suma(String mensaje, Socket sc, String name) {
        System.out.println("Cliente " + name + " -  Ejecutando servicio: suma.");
        Calculator ca = new Calculator();
        String res = ca.suma(mensaje);
        se.send(res, sc);
        return 1;
    }

    public int colebrook(String mensaje, Socket sc, String name) {
        System.out.println("Cliente "+name+" -  Ejecutando servicio: colebrook.");
        Colebrook co = new Colebrook();
        String res = co.setDatos(mensaje);
        se.send(res, sc);
        return 1;
    }

    public int app(String mensaje, Socket sc, String name) {
        System.out.println("Cliente "+name+" -  Ejectuando servicio: App.");
        Aplication ac = new Aplication();
        String res = ac.app(mensaje);
        se.send(res, sc);
        return 1;
    }
}
