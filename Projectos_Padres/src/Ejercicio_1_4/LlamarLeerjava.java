package Ejercicio_1_4;

import java.io.*;
import java.util.Scanner;

public class LlamarLeerjava {

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder("/usr/bin/java", "Ejercicio4_Ejercicio1.Leerjava");
        pb.directory(new File("/home/alumno/Escritorio/PSP DE Dam/PSP_DAM2_Eclipse_workspace/bin"));
        pb.redirectErrorStream(true);

        Process p = pb.start();

        // 1. ESCRIBIR primero en el stdin del hijo
        PrintWriter escritura = new PrintWriter(p.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un nombre: ");
        String nombre = sc.nextLine();
        escritura.println(nombre);
        escritura.close();  // el hijo ve EOF y sabe que no hay más input

        // 2. LEER después toda la salida (el hijo ya terminó o está terminando)
        BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder salida = new StringBuilder();
        String linea;
        while ((linea = lector.readLine()) != null) {
            
        	salida.append(linea).append("\n");
        }

        // 3. Esperar y mostrar código de salida
        int retorno = p.waitFor();
        System.out.println("Código de salida: " + retorno);
        if (retorno == 0) {
            System.out.println(salida.toString());
        } else {
            System.out.println("Error: nombre demasiado corto");
        }
    }
}   