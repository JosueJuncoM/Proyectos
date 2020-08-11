package socket_servidor.model;

public class Calculator {

    public double a;
    public double b;

    public String suma(String mensaje) {
        String[] parts = mensaje.split(" ");    //Se separan los dos numeros y se almacenan en parts[]
        String res = "";
        //Se convierten los datos en enteros
        try {
            a = Double.valueOf(parts[1]);
            b = Double.valueOf(parts[2]);
            res = "  Resultado de la suma: " + (a + b);
        } catch (Exception e) {
            res = "  Los numeros ingresados no son validos.";
        }
        return res;  //Se retorna el resultado de la suma
    }
}
