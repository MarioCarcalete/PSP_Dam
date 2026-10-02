package Ejercicio3;

import java.text.Normalizer;

public class Ejercicio3 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No has escrito nada");
            System.exit(-1);
        }

        String texto = String.join(" ", args);

        String limpio = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();

        String invertido = new StringBuilder(limpio).reverse().toString();

        if (!limpio.isEmpty() && limpio.equals(invertido)) {
            System.out.println("Es palíndromo");
        } else {
            System.out.println("NO es palíndromo");
        }
        System.exit(0);
    }
}