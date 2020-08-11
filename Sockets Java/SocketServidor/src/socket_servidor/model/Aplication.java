package socket_servidor.model;

import java.io.IOException;

public class Aplication {

    public String app(String com) {
        String est = "";
        String[] parts = com.split(" ");
        if (parts[1].equals("?")) {
            est = "Lista de apps:"
                    + "\n  CALC: abre la calculadora."
                    + "\n  CHARMAP: abre la tabla de caracteres."
                    + "\n  CMD: abre la consola."
                    + "\n  EXPLORER: abre el explorador de Windows."
                    + "\n  IEXPLORE: abre Internet Explorer."
                    + "\n  MAGNIFY: abre la lupa."
                    + "\n  MSINFO32: abre la ventana de información del sistema."
                    + "\n  MSPAINT: abre Paint."
                    + "\n  NOTEPAD: abre el bloc de notas."
                    + "\n  OSK: muestra el teclado en pantalla."
                    + "\n  SNDVOL: ajusta las propiedades del volumen."
                    + "\n  SOUNDRECORDER: abre la grabadora de sonidos."
                    + "\n  TASKMGR: abre el administrador de tareas de Windows."
                    + "\n  WINVER: abre la ventana Acerca de Windows (para conocer la versión de Windows)."
                    + "\n  WINWORD: abre Word (si está instalado)."
                    + "\n  WMPLAYER: abre Windows Media Player."
                    + "\n  WRITE o Wordpad: abre WordPad.";
        } else {
            try {
                String cmd = parts[1];
                Runtime.getRuntime().exec(cmd);
                est = "Aplicacion ejecutada en el servidor.";
            } catch (IOException ioe) {
                est = "La plaicacion no se puede ejecutar o no es valida.";
            }
        }
        return est;
    }
}
