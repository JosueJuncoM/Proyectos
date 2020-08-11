package socket_cliente.model;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import socket_cliente.model.Messenger;

public class Commands {

    public Messenger ms = new Messenger();
    public Socket sc;

    //clear
    public void clear() {
        try {
            new ProcessBuilder("cls", "&&", "java -cp SocketCliente.jar socket_cliente.Capturator").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {

        }
    }

    //Start
    public int start(String HOST, String PUERT, String command) {
        int estado = 0;
        int PUERTO = 0;
        int verport = 0;
        //verificar host
        String phost = HOST.replace(".", " ");
        String[] p = phost.split(" ");
        if (p.length == 0 || p.length < 4) {
            System.out.println("  El Host: " + HOST + " no es valido.");
        } else {
            try {
                PUERTO = Integer.valueOf(PUERT);
                verport = 1;
            } catch (NumberFormatException e) {
                System.out.println("El puerto no es valido.");
            }

            //Si esta bien, verifica que se pueda conectar
            if (verport == 1) {
                try {
                    sc = new Socket(HOST, PUERTO); //Creacion del socket cliente con ip y host
                    estado = ms.cliente(sc, command, 1);
                } catch (IOException ex) {
                    System.out.println("Error al conectar con el servidor: " + ex);
                    estado = 0;
                }
            }
        }
        return estado;
    }

    //Disconnect
    public int disconnect(String command) {
        ms.cliente(sc, command, 0);
        try {
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Suma, Colebrook, Aplicacion
    public void servicio(String command) {
        ms.cliente(sc, command, 1);
    }

    //help
    public void help() {
        System.out.println("Lista de comandos:"
                + "\n  start: Conectarce a un servidor necesita Host y puerto (Ej. start x.x.x.x xxxx)."
                + "\n  disconnect: Desconectarse del servidor actual."
                + "\n  suma: Suma dos numeros (Ej. suma 1 2). "
                + "\n        Necesita estar conectado a un servidor."
                + "\n  colebrook: Encuetra el factor de friccion."
                + "\n             Necesita numero de Reynolds y Rugosidad. (Ej. colebrook 800000 0.04)."
                + "\n             Necesita estar conectado a un servidor."
                + "\n  app: Ejecuta una aplicacion en el servidor. (Ej. app notepad)."
                + "\n       Puede ver la lista de aplicaciones con (app ?)."
                + "\n       Necesita estar conectado a un servidor."
                + "\n  clear: Limpia la pantalla (proximamente...)."
                + "\n  exit: Finaliza el programa."
                + "\n        Necesita desconectarce del servidor si lo esta."
                + "\n  help: Lista de comandos.");
    }

    //serv
    public void serv(String command) {
        ms.cliente(sc, command, 1);
    }
}
