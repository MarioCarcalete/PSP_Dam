
package Ejercicio1_Multiproceso2;


public class Ejercicio1_H {
    public static void main(String[] args) {
        if (args.length == 0 || args[0].isEmpty()) {
            System.exit(-1);
        }

        try {
            int n = Integer.parseInt(args[0]);
            if (n > 0) {
                System.exit(-3);
            } else if (n < 0) {
                System.exit(0);
            }
            // Si n == 0, se sale con 0 por defecto (no cubierto por el enunciado)
        } catch (NumberFormatException e) {
            System.exit(-2);
        }
    }
}   