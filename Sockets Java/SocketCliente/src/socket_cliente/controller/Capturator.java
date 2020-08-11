package socket_cliente.controller;

import socket_cliente.controller.ReadComClient;
import java.util.Scanner;

public class Capturator {

    public static void main(String args[]) {
        Scanner leer = new Scanner(System.in);
        ReadComClient rcl = new ReadComClient();
        //Capturar el comando
        System.out.print(">>");
        String com = leer.nextLine();
        int es = rcl.readComand(com);
        while (es == 1) {
            com = leer.nextLine();
            es = rcl.readComand(com);
        }
    }
}
