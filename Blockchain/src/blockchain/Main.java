package blockchain;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Chain c = new Chain();
        //creacion del bloque genesis
        c.genesis();
        
        Scanner leer = new Scanner(System.in);
        boolean x = true;
        do {
            System.out.println("BLOCKCHAIN.\nMenu:\n  1. Ingresar Transaccion.\n  2. Salir");
            System.out.print("Opcion: ");
            int opc = leer.nextInt();
            switch (opc) {
                case 1:
                    
                    System.out.println("\nIngrese una transaccion (origen-monto-destino):");
                    String tx = leer.nextLine();
                    tx = leer.nextLine();
                    c.addTransaction(tx);
                    c.printBlock();
                    break;
                default:
                    x = false;
                    break;
            }

        } while (x);
    }
}
