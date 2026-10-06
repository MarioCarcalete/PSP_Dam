package Apuntes_Padres;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

/*
 * ============================================================
 *  PROCESO PADRE - CÓDIGO DE APUNTES (PSP - UT1)
 *  Proyecto: PSP_Padres   ·   Paquete: apuntes
 * ============================================================
 *
 *  El PADRE es el que tiene el ProcessBuilder: prepara, lanza,
 *  se comunica con el hijo y recoge cómo ha terminado.
 *
 *  Las dos clases clave (paquete java.lang, no hay que importarlas):
 *    - ProcessBuilder -> PREPARA el proceso (comando, carpeta, redirecciones)
 *    - Process        -> CONTROLA el proceso ya lanzado (streams, waitFor, destroy)
 *
 *  Las 3 tuberías, vistas DESDE EL PADRE (por eso parecen al revés):
 *    p.getOutputStream() -> el padre ESCRIBE  -> llega al System.in   del hijo
 *    p.getInputStream()  -> el padre LEE      <- lo que imprime System.out del hijo
 *    p.getErrorStream()  -> el padre LEE      <- lo que imprime System.err del hijo
 *    p.waitFor()         -> espera y recoge el System.exit(n) del hijo
 *
 *  Estructura del workspace (los dos proyectos en el mismo workspace):
 *    workspace/
 *      PSP_Hijos/bin/apuntes/HijoApuntes.class   <- lo que se ejecuta
 *      PSP_Padres/src/apuntes/PadreApuntes.java  <- este fichero
 *
 *  ANTES DE EJECUTAR: ejecuta una vez HijoApuntes en Eclipse para que
 *  se compile y exista su .class en PSP_Hijos/bin.
 *
 *  Este programa enseña las 3 formas de pasar datos al hijo:
 *    1) Por ARGUMENTO
 *    2) Por ENTRADA ESTÁNDAR (escribiendo en su stream)
 *    3) Por FICHEROS (redirigiendo entrada y salidas)
 */
public class Apuntes {

    // Carpeta donde están los .class del hijo.
    // Ruta RELATIVA: Eclipse ejecuta el padre desde la carpeta PSP_Padres,
    // así que ".." sube al workspace y entra en PSP_Hijos/bin.
    static final File CARPETA_HIJO = new File("../PSP_Hijos/bin");

    // Nombre COMPLETO de la clase del hijo: paquete.Clase (¡mayúsculas exactas!)
    static final String CLASE_HIJO = "apuntes.HijoApuntes";

    public static void main(String[] args) throws IOException, InterruptedException {
        ejemploArgumento("25");
        ejemploArgumento("hola");                       // provoca el error 2 del hijo
        ejemploEntradaEstandar(new String[] {"3", "5", "6", "*"});
        ejemploFicheros();
    }

    // =============================================================
    // 1) PASAR EL DATO POR ARGUMENTO
    //    El hijo lo recibe en args[0].
    // =============================================================
    static void ejemploArgumento(String dato) throws IOException, InterruptedException {
        System.out.println("\n===== 1) Por argumento: " + dato + " =====");

        // Cada palabra del comando en su propio String.
        // Los argumentos para el hijo van AL FINAL.
        ProcessBuilder pb = new ProcessBuilder("java", CLASE_HIJO, dato);

        // Carpeta de trabajo del hijo = donde están sus .class
        pb.directory(CARPETA_HIJO);

        // start() crea el proceso hijo. A partir de aquí, padre e hijo
        // se ejecutan a la vez (concurrentemente).
        Process p = pb.start();

        // Leemos lo que el hijo imprime y esperamos a que termine.
        mostrarResultado(p);
    }

    // =============================================================
    // 2) PASAR DATOS POR ENTRADA ESTÁNDAR
    //    El padre escribe en p.getOutputStream() y el hijo lo lee
    //    de System.in como si lo hubieras escrito tú con el teclado.
    // =============================================================
    static void ejemploEntradaEstandar(String[] datos) throws IOException, InterruptedException {
        System.out.println("\n===== 2) Por entrada estándar =====");

        ProcessBuilder pb = new ProcessBuilder("java", CLASE_HIJO);   // sin argumentos
        pb.directory(CARPETA_HIJO);
        Process p = pb.start();

        // PrintWriter con autoflush (true): cada println se envía al momento.
        // try-with-resources: al salir del bloque se hace close() automáticamente.
        try (PrintWriter haciaHijo = new PrintWriter(p.getOutputStream(), true)) {
            for (String d : datos) {
                haciaHijo.println(d);   // println añade el salto de línea: ¡IMPRESCINDIBLE!
            }
        }
        // Al cerrar, el hijo recibe "fin de datos" (hasNextLine() pasa a false).
        // Si no cerramos, un hijo que lee hasta el final se quedaría esperando para siempre.

        mostrarResultado(p);
    }

    // =============================================================
    // 3) USAR FICHEROS (REDIRECCIÓN)
    //    Se configura en el ProcessBuilder ANTES de start().
    //    El hijo no cambia nada: sigue usando System.in y System.out.
    // =============================================================
    static void ejemploFicheros() throws IOException, InterruptedException {
        System.out.println("\n===== 3) Con ficheros =====");

        // Las rutas relativas se crean en la carpeta del PADRE (PSP_Padres),
        // aunque el hijo trabaje en otra carpeta.
        File entrada = new File("entrada.txt");
        File salida = new File("salida.txt");
        File error = new File("error.txt");

        // Preparamos un fichero de entrada de ejemplo (en un ejercicio real ya existiría).
        Files.writeString(entrada.toPath(), "10\n20\n30\n*\n");

        ProcessBuilder pb = new ProcessBuilder("java", CLASE_HIJO);
        pb.directory(CARPETA_HIJO);
        pb.redirectInput(entrada);   // System.in  del hijo <- entrada.txt
        pb.redirectOutput(salida);   // System.out del hijo -> salida.txt (se sobrescribe)
        pb.redirectError(error);     // System.err del hijo -> error.txt
        // Para AÑADIR al final en vez de sobrescribir:
        // pb.redirectOutput(ProcessBuilder.Redirect.appendTo(salida));

        Process p = pb.start();

        // Como la salida va a un fichero, getInputStream() ya no tiene nada.
        // Solo esperamos y después leemos el fichero.
        int codigo = p.waitFor();
        System.out.println("Código de salida: " + codigo);

        if (codigo == 0) {
            System.out.print("Contenido de salida.txt:\n" + Files.readString(salida.toPath()));
        } else {
            System.out.print("Contenido de error.txt:\n" + Files.readString(error.toPath()));
        }
    }

    // =============================================================
    // MÉTODO DE AYUDA: leer la salida del hijo, esperar y decidir.
    // ORDEN CORRECTO: 1º leer todo   2º waitFor()
    // (si el hijo escribe mucho y nadie lee, se llena la tubería y
    //  padre e hijo se quedan esperándose: un interbloqueo)
    // =============================================================
    static void mostrarResultado(Process p) throws IOException, InterruptedException {
        // Leer lo que el hijo escribió con System.out
        StringBuilder salida = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {   // null = el hijo cerró su salida
                salida.append(linea).append("\n");
            }
        }

        // Leer lo que el hijo escribió con System.err
        String error = new String(p.getErrorStream().readAllBytes());

        // Esperar a que termine (como máximo 10 segundos)
        boolean terminado = p.waitFor(10, TimeUnit.SECONDS);
        if (!terminado) {
            p.destroy();   // si se ha colgado, lo matamos
            System.out.println("El hijo no respondía y se ha destruido");
            return;
        }

        // exitValue() solo se puede usar cuando ya ha terminado
        int codigo = p.exitValue();
        System.out.println("Código de salida: " + codigo);

        // Decidir qué mostrar según el código que acordamos con el hijo
        switch (codigo) {
            case 0 -> System.out.print(salida);
            case 2 -> System.out.println("Error: el argumento no es un entero -> " + error.trim());
            case 3 -> System.out.println("Error: hay una línea que no es un número -> " + error.trim());
            case 4 -> System.out.println("Error: no se ha enviado ningún dato");
            case 1 -> System.out.println("El hijo ha fallado (¿ruta o nombre de clase mal?):\n" + error);
            // El 1 lo devuelve Java cuando el programa lanza una excepción no capturada,
            // por ejemplo ClassNotFoundException si CARPETA_HIJO o CLASE_HIJO están mal.
            default -> System.out.println("Código inesperado " + codigo + ":\n" + error);
        }
    }
}