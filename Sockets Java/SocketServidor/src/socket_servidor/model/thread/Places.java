package socket_servidor.model.thread;

import java.net.Socket;

public class Places {

    public int place;                       //Variable paar gestionar los espacios del servidor
    public int ini;                         //Variable para verificar que todos los espacios se hallan ocupado
    public Process c1;                      //Espacio 1 del servidor
    public Process c2;                      //Espacio 2 del servidor
    public Process c3;                      //Espacio 3 del servidor
    public Process c4;                      //Espacio 3 del servidor
    public Process c5;                      //Espacio 3 del servidor

    public int getPlace() {
        return place;
    }

    public void setIni(int ini) {
        this.ini = ini;
    }

    public int getIni() {
        return ini;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    //Metodo para ejecutar un proceso para cada cliente
    public void con(int PUERTO, Socket sc) {
        //System.out.println("place:" + place);
        if (place == 1) {
            place = 2;
            c1 = new Process("1", PUERTO + 1, sc);
            c1.start();
        } else {
            if (place == 2) {
                place = 3;
                c2 = new Process("2", PUERTO + 2, sc);
                c2.start();
            } else {
                if (place == 3) {
                    place = 4;
                    c3 = new Process("3", PUERTO + 3, sc);
                    c3.start();
                } else {
                    if (place == 4) {
                        place = 5;
                        c4 = new Process("4", PUERTO + 4, sc);
                        c4.start();
                    } else {
                        if (place == 5) {
                            ini = 1;
                            c5 = new Process("5", PUERTO + 5, sc);
                            c5.start();
                        }
                    }
                }
            }
        }

    }

    //metodo para verificar que hallan espacios disponibles en el servidor
    public boolean verEstadohilos() {
        if (c1.isAlive() == false) {
            place = 1;
            return true;
        } else {
            if (c2.isAlive() == false) {
                place = 2;
                return true;
            } else {
                if (c3.isAlive() == false) {
                    place = 3;
                    return true;
                } else {
                    if (c4.isAlive() == false) {
                        place = 4;
                        return true;
                    } else {
                        if (c5.isAlive() == false) {
                            place = 5;
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
    }
}
