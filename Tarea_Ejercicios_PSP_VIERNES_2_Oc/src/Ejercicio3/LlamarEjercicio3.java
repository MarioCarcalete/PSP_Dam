package Ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class LlamarEjercicio3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un texto:");
        String texto = sc.nextLine();

        String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pb = new ProcessBuilder(java, "-Dstdout.encoding=UTF-8", "-cp", classpath,
                "ejercicio3.Ejercicio3", texto);

        try {
            Process p = pb.start();

            StringBuilder respuesta = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    respuesta.append(linea).append(System.lineSeparator());
                }
            }

            int valor = (byte) p.waitFor();
            System.out.println("Valor de Salida: " + valor);
            System.out.print(respuesta);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al lanzar el proceso: " + e.getMessage());
        }
        sc.close();
    }
}