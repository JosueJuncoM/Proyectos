package socket_servidor.controller;

import socket_servidor.model.Commands;
import java.net.Socket;

public class ReadComServer {

    Commands co = new Commands();

    public int readCommand(String com, Socket sc, String numCliente) {
        int estadoConexion = 0;
        String[] partsCom = com.split(" ");
        String command = partsCom[0].replace(" ", "");

        //start
        if (command.equals("start")) {
            estadoConexion = co.start(sc);
        }

        //disconnect
        if (command.equals("disconnect")) {
            estadoConexion = co.desconectar(sc, numCliente);
        }

        //suma
        if (command.equals("suma")) {
            estadoConexion = co.suma(com, sc, numCliente);
        }

        //colebrook
        if (command.equals("colebrook")) {
            estadoConexion = co.colebrook(com, sc, numCliente);
        }

        //app
        if (command.equals("app")) {
            estadoConexion = co.app(com, sc, numCliente);
        }

        return estadoConexion;
    }
}
