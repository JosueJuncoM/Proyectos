package socket_cliente.controller;

import socket_cliente.model.Commands;

public class ReadComClient {

    public Commands co = new Commands();
    public int estadoConsola = 0;
    public int estadoConexion = 0;
    public int vercom = 0;

    public int readComand(String com) {
        String[] partsCom = com.split(" ");    
        String command = partsCom[0].replace(" ", "");
        String[] listcom = {"start", "disconnect", "app", "exit", "help", "colebrook", "suma", "clear", "oserv"};

        //verifica si el comando existe.
        for (int i = 0; i < listcom.length; i++) {
            if (command.equals(listcom[i])) {
                vercom = 1;
                i = listcom.length;
            } else {
                vercom = 0;
            }
        }

        //Si el comando existe
        if (vercom == 1) {

            //clear
            if (command.equals("clear")) {
                System.out.println("  Proximamente...");
                estadoConsola = 1;
                System.out.print("\n>>");
            }

            //Start
            if (command.equals("start")) {
                if (estadoConexion == 0) {
                    if (partsCom.length > 3) {
                        System.out.println("    Error al ejecutar el comando."
                                + "\n    Modo de ejecucion 'start #host #puerto'");
                    } else {
                        if (partsCom.length == 2) {
                            System.out.println("  Falta ingresar el puerto.");
                        } else {
                            if (partsCom.length == 1) {
                                System.out.println("  Faltan datos (Host y Puerto).");
                            } else {
                                int es = co.start(partsCom[1], partsCom[2], com);
                                estadoConexion = es;
                            }
                        }
                    }
                } else {
                    System.out.println("  Ya esta conectado a un servidor.");
                }
                estadoConsola = 1;
                System.out.print("\n>>");
            }

            //Desconectar
            if (command.equals("disconnect")) {
                if (estadoConexion == 1) {
                    int es = co.disconnect(command);
                    estadoConexion = es;
                    System.out.println("  Se a desconectado del servidor.");
                } else {
                    System.out.println("  No se encuentra conectado a un servidor.");
                }
                estadoConsola = 1;
                System.out.print("\n>>");
            }

            //Salir
            if (command.equals("exit")) {
                if (estadoConexion == 1) {
                    System.out.println("  Necesita desconectarse del servidor.");
                    estadoConsola = 1;
                    System.out.print("\n>>");
                } else {
                    estadoConsola = 0;
                    System.out.println("  Finalizando programa.");
                }
            }

            //Suma
            if (command.equals("suma")) {
                if (estadoConexion == 1) {
                    if (partsCom.length > 3) {
                        System.out.println("    Error al ejecutar el comando."
                                + "\n    Modo de ejecucion 'suma #num1 #num2'");
                    } else {
                        if (partsCom.length < 3) {
                            try {
                                Double.valueOf(partsCom[1]);
                            } catch (Exception e) {
                                System.out.println("  El numero '" + partsCom[1] + "' no es valido.");
                            }
                            System.out.println("  Faltan datos.");
                        } else {
                            co.servicio(com);
                        }
                    }
                } else {
                    System.out.println("  Necesita conectarse a un servidor.");
                }
                estadoConsola = 1;
                System.out.print("\n>>");
            }

            //colebrook
            if (command.equals("colebrook")) {
                if (estadoConexion == 1) {
                    if (partsCom.length > 3) {
                        System.out.println("    Error al ejecutar el comando."
                                + "\n    Modo de ejecucion 'colebrook #Reynolds #Rugosidad'");
                    } else {
                        if (partsCom.length < 3) {
                            try {
                                Double.valueOf(partsCom[1]);
                            } catch (Exception e) {
                                System.out.println("  El valor de Reynolds '" + partsCom[1] + "' no es valido.");
                            }
                            System.out.println("  Faltan datos. (Ej. colebrook #Reynolds #Rugosidad)");
                        } else {
                            co.servicio(com);
                        }
                    }
                } else {
                    System.out.println("  Necesita conectarse a un servidor.");
                }
                estadoConsola = 1;
                System.out.print("\n>>");
            }

            //app
            if (command.equals("app")) {
                if (estadoConexion == 1) {
                    if (partsCom.length > 2) {
                        System.out.println("    Error al ejecutar el comando."
                                + "\n    Modo de ejecucion 'app #comandoapp'");
                    } else {
                        if (partsCom.length < 2) {
                            System.out.println("  Faltan datos. Use 'app ?'.");
                        } else {
                            co.servicio(com);
                        }
                    }
                } else {
                    System.out.println("  Necesita conectarse a un servidor.");
                }
                estadoConsola = 1;
                System.out.print("\n>>");
            }

            //help
            if (command.equals("help")) {
                co.help();
                estadoConsola = 1;
                System.out.print("\n>>");
            }

            //serv
            if (command.equals("oserv")) {
                String[] partComServ = com.split("-");  
                if (estadoConexion == 1) {
                    co.serv(partComServ[1]);
                }else{
                    System.out.println("  Necesita conectarse a un servidor.");
                }
                estadoConsola = 1;
                System.out.print("\n>>");
            }

        } else {
            System.out.println("  No se reconoce el comando " + command + "."
                    + "\n    Use 'help' para ver la lista de comandos.");
            estadoConsola = 1;
            System.out.print("\n>>");
        }
        return estadoConsola;
    }
}
