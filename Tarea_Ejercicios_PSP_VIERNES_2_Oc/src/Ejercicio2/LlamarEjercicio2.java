package Ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LlamarEjercicio2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> datos = new ArrayList<>();
        String dato;

        do {
            System.out.println("Escribe un número:");
            dato = sc.nextLine();
            datos.add(dato);
        } while (!dato.trim().equals("*"));

        String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        ProcessBuilder pb = new ProcessBuilder(java, "-cp", classpath, "ejercicio2.Ejercicio2");

        try {
            Process p = pb.start();

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                for (String d : datos) {
                    bw.write(d);
                    bw.newLine();
                }
            } catch (IOException e) {
            }

            StringBuilder salidaHijo = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    salidaHijo.append(linea).append(System.lineSeparator());
                }
            }

            int valor = (byte) p.waitFor();
            System.out.println("Valor de Salida: " + valor);

            if (valor == 0) {
                System.out.print(salidaHijo);
            } else {
                System.out.println("Error: has introducido algo que no es un número");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al lanzar el proceso: " + e.getMessage());
        }
        sc.close();
    }
}