package Ejercicio1;

public class Ejercicio1 {

    public static void main(String[] args) {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.exit(-1);
        }

        int numero;
        try {
            numero = Integer.parseInt(args[0].trim());
        } catch (NumberFormatException e) {
            System.exit(-2);
            return;
        }

        if (numero > 0) {
            System.exit(-3);
        } else {
            System.exit(0);
        }
    }
}