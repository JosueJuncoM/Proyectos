package blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
Clase Hash para calcular el hash final al momento de cerrar un bloque
con los datos del mismo (id, transacciones, hash preview, nonce).

Tambien se calcula la prueba de trabajo aleatoria para que se cumpla 
la condicon del hash (tres ceros al inicio) y asi cerrar el bloque.
*/

public class Hash {
    
    //metodo para calcular el hash y la prueba de trabajo
    /*
    Ingresa por parametro el bloque actual para la obtencion de los datos del mismo.
    La variable String data contiene la concatencaion de los datos del bloque.
    
    El ciclo while comprueba que la condicion del hash se cumpla y en caso de no ser
    asi, cambia aleatoriamente el valor de la prueba de trabajo para que se cumpla
    */
    public String calcHash(Block b) {
        String hash = "";
        String data = b.getId() + b.getHashprev();
        String[] tran = b.getTrans();
        for (int i = 0; i < tran.length; i++) {
            data = data + tran[i];
        }

        int nonce = 0;
        boolean ha = false;
        while (!ha) {
            nonce = (int) (Math.random() * 100000 + 1);
            data = data + nonce;
            hash = convertirSHA256(data);
            String condicion = hash.substring(0, 3);
            if (condicion.equals("000")) {
                ha = true;
            }
        }

        return hash + "-" + nonce;

    }
    
    /*
    Metodo para calcular el hash de los datos del bloque almacenados en la
    String data implementando el algoritmo SHA-256.
    
    El resultado del retorno es un String que contiene el hash.
    */
    public String convertirSHA256(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(data.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
