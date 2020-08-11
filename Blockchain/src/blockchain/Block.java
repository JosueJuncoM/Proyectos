package blockchain;

/*
*Clase block que contiene el objeto bloque compuesto por:
 Id del bloque.
 Prueba de trabajo (nonce).
 Vector con las transacciones del bloque.
 Hash del anterior bloque de la cadena.
 Hash de cierre de bloque.
*/

public class Block {

    public int id;
    public int nonce;
    public String[] trans;
    public String hashprev;
    public String hash;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String[] getTrans() {
        return trans;
    }

    public void setTrans(String[] trans) {
        this.trans = trans;
    }

    public String getHashprev() {
        return hashprev;
    }

    public void setHashprev(String hashprev) {
        this.hashprev = hashprev;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
