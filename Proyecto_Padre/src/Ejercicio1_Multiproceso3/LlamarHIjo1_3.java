package Ejercicio1_Multiproceso3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LlamarHIjo1_3 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Analizar los datos");

        String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

        String rutaHijo = ".." + File.separator + "Proyecto_HIjos";
        String classpathHijo = "bin";

        ProcessBuilder pb = new ProcessBuilder(java, "-cp", classpathHijo, "Ejercicio1_Multiproceso3.Ejercicio1");
        pb.directory(new File(rutaHijo));
        pb.inheritIO();

        try (BufferedReader lector = new BufferedReader(new FileReader(new File(rutaHijo, "datos_todos.txt")))) {
            String linea;
            while ((linea = lector.readLine()) != null) {

                try (PrintWriter pw = new PrintWriter(new FileWriter(new File(rutaHijo, "datos.txt")))) {
                    pw.println(linea);
                }

                Process p = pb.start();
                int codigo = (byte) p.waitFor();

                System.out.print(linea + " → ");
                switch (codigo) {
                    case -3 -> System.out.println("Correcto: entero positivo");
                    case -1 -> System.out.println("Incorrecto: vacío");
                    case -2 -> System.out.println("Incorrecto: no es un entero");
                    case 0 -> System.out.println("Incorrecto: no es positivo");
                    default -> System.out.println("Código inesperado: " + codigo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Proceso interrumpido");
        }
    }
}