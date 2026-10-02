package Ejercicio1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LlamarEjercicio1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un número entero positivo:");
        String dato = sc.nextLine();

        String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pb = new ProcessBuilder(java, "-cp", classpath, "ejercicio1.Ejercicio1", dato);

        try {
            Process p = pb.start();
            int salida = (byte) p.waitFor();

            switch (salida) {
                case -1 -> System.out.println("No has escrito nada");
                case -2 -> System.out.println("No has escrito un entero");
                case -3 -> System.out.println("Has escrito un entero positivo");
                case 0 -> System.out.println("El entero debe ser positivo");
                default -> System.out.println("Valor de salida inesperado: " + salida);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al lanzar el proceso: " + e.getMessage());
        }
        sc.close();
    }
}