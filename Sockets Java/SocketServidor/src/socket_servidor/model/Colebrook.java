package socket_servidor.model;

public class Colebrook {

    public double f;
    public double reyn;
    public double ed;
    public double marE = 0.000000000000001;
    public double jmp = 0;
    public int it = 0;

    public String setDatos(String datos) {
        String[] parts = datos.split(" ");    //Se separan los dos numeros y se almacenan en parts[]
        String res = "";
        try {
            reyn = Double.valueOf(parts[1]);
            ed = Double.valueOf(parts[2]);
            res = "Resultado: " + run();
        } catch (Exception e) {
            res = "  Los valores ingresados no son validos.";
        }
        return res;
    }

    public String run() {
        jumpU(0.02);
        return jmp + "\nF: " + f + "\nIt: " + it;
    }

    public void jumpU(double j) {
        do {
            jmp = jmp + j;
            f = eq(jmp);
            it = it + 1;
        } while ((1 - f) > marE);
        if ((1 - f) < 0) {
            jumpD(j / 2);
        }
    }

    public void jumpD(double j) {
        do {
            jmp = jmp - j;
            f = eq(jmp);
            it = it + 1;
        } while ((1 - f) < 0);
        if ((1 - f) > 0) {
            jumpU(j / 2);
        }
    }

    public double eq(double seed) {
        double fr = -2 * Math.log10((ed / 3.7) + (2.51 / (reyn * Math.sqrt(seed)))) * Math.sqrt(seed);
        return fr;
    }
}
