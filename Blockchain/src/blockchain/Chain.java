package blockchain;

import java.util.ArrayList;
import java.util.Random;

/*
Clase Chain que contien un ArrayList para el almacenamiento de los bloques
con los metodos de añadir transaccion, creacion del bloque genesis y añadir un nuevo bloque

Varaibles utilizadas:

contBlock: contador de bloques creados.
contTx: contador de las transacciones por bloque.
ArrayList chain: Almacena los objetos de la calse Block.
tran: vector para guardar las transacciones temporalmente.
Random r: Objeto para generar un numero random.
Hash h: objeto de la calse hash.
 */
public class Chain {

    public int contBlock = 1;
    public int contTx = 0;
    public ArrayList<Block> chain = new ArrayList();
    public String[] tran = new String[5];
    public Random r;
    public Hash h = new Hash();

    public ArrayList<Block> getChain() {
        return chain;
    }

    /*
    Metodo genesis crea el primer bloque de la cadena el cual contiene la primer transaccion
    y el hash preview que en este caso son 64 ceros al no existir un bloque anterior.
     */
    public void genesis() {
        Block block = new Block();
        int nonce = (int) (Math.random() * 100000 + 1);
        String hprev = "0000000000000000000000000000000000000000000000000000000000000000";
        String hash = "";
        block.setId(contBlock);
        block.setNonce(nonce);
        tran[contTx] = "wallet0-50-wallet1";
        contTx++;
        block.setTrans(tran);
        block.setHashprev(hprev);
        block.setHash(hash);
        chain.add(block);

    }

    /*
    El metodo addTransaction ingresa las transacciones al bloque actual y comprueba que
    el limite de transacciones por bloque se cumplan (5 transacciones por bloque), 
    ademas de indicar cuando se cierra el bloque, para hacer un llamado a los metodos
    de la clase hash y cerrar el bloque.
     */
    public void addTransaction(String tx) {
        if (contTx == 5) {
            
            contTx = 0;

            //Calcular hash y nonce
            System.out.println("\n      !!!Limite de transacciones alcanzado!!!"
                    + "\n      Cerrando bloque: Calculando hash y prueba de trabajo.\n");
            String resp = h.calcHash(chain.get(contBlock - 1));
            String[] data = resp.split("-");
            chain.get(contBlock - 1).setHash(data[0]);
            chain.get(contBlock - 1).setNonce(Integer.parseInt(data[1]));

            //crear nuevo bloque
            contBlock++;

            printBlock();
            int i = 0;
            while (i < 5) {
                tran[i] = null;
                i++;
            }
            addBlock(tx);
            System.out.println("\n      ---BLOQUE AGREGADO A LA CADENA, CREANDO NUEVO BLOQUE---\n");

        } else {
            tran[contTx] = tx;
            contTx++;
            chain.get(contBlock - 1).setTrans(tran);
        }
    }

    /*
    Metodo addBlock añade un nuevo bloque a la cadena cuando se ha cerrado el bloque anterior.
    Contiene el nuevo id, la transaccion que no ingreso en el ultimo bloque y el hash preview
    correspondiente al hash del ultimo bloque cerrado.
     */
    public void addBlock(String tx) {
        Block blo = new Block();
        int nonce = (int) (Math.random() * 100000 + 1);
        blo.setId(contBlock);
        blo.setNonce(nonce);
        tran[contTx] = tx;
        contTx++;
        blo.setTrans(tran);
        String hprev = chain.get(chain.size() - 1).getHash();
        blo.setHashprev(hprev);
        String hash = "";
        blo.setHash(hash);
        chain.add(blo);
    }

    /*
    Metodo para imprimir la informacion del bloque actual.
     */
    public void printBlock() {
        System.out.println("\n----Informacion Bloque----"
                + "\nId: " + chain.get(chain.size() - 1).getId()
                + "\nNonce: " + chain.get(chain.size() - 1).getNonce()
                + "\nTransacciones: ");
        String[] tx;
        tx = chain.get(chain.size() - 1).getTrans();
        for (int i = 0; i < tx.length; i++) {
            if (tx[i] != null) {
                System.out.println("* " + tx[i]);
            }
        }

        System.out.println("Hash Preview: " + chain.get(chain.size() - 1).getHashprev()
                + "\nHash: " + chain.get(chain.size() - 1).getHash()
                + "\n---------------------------------");
    }

 

}
