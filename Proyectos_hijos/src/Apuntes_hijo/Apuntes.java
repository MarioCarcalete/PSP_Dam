package Apuntes_hijo;

import java.util.Scanner;

/*
 * ============================================================
 *  PROCESO HIJO - CÓDIGO DE APUNTES (PSP - UT1)
 *  Proyecto: PSP_Hijos   ·   Paquete: apuntes
 * ============================================================
 *
 *  Un HIJO es un programa Java NORMAL. No sabe quién lo lanza:
 *  puede ser el padre, tú desde Eclipse o la terminal.
 *
 *  Solo hace 3 cosas:
 *    1. RECIBIR datos  -> por ARGUMENTOS (args) o por ENTRADA ESTÁNDAR (System.in)
 *    2. DEVOLVER texto -> por SALIDA ESTÁNDAR (System.out) o de ERROR (System.err)
 *    3. TERMINAR con un CÓDIGO DE SALIDA -> System.exit(n)
 *         0  = todo ha ido bien (convención)
 *         otro número = algún error (tú decides qué significa cada uno)
 *
 *  OJO con los códigos negativos: el sistema operativo solo guarda
 *  valores de 0 a 255, así que System.exit(-1) llega al padre como 255.
 *  Por eso aquí uso códigos positivos. Y empiezo en 2 porque el 1
 *  ya lo usa Java cuando el programa revienta con una excepción
 *  (por ejemplo, si no encuentra la clase).
 *
 *  REGLA DE ORO: el hijo NO muestra mensajes tipo "Escribe un número:".
 *  Todo lo que imprime lo recibe el padre como resultado.
 *  El que habla con el usuario es el PADRE.
 *
 *  Este hijo funciona de dos formas:
 *    A) Si recibe un ARGUMENTO  -> comprueba si es un número entero.
 *    B) Si NO recibe argumentos -> lee líneas de System.in y suma los
 *                                  números hasta que llegue un "*"
 *                                  o se acabe la entrada.
 *
 *  Códigos de salida de este hijo:
 *    0 -> correcto
 *    2 -> el argumento no es un número entero
 *    3 -> una línea de la entrada no es un número
 *    4 -> no ha llegado ningún dato por la entrada
 */
public class Apuntes {

    public static void main(String[] args) {

        // ---------------------------------------------------------
        // FORMA A: DATOS POR ARGUMENTO
        // El padre los pone al final del comando del ProcessBuilder:
        //   new ProcessBuilder("java", "apuntes.HijoApuntes", "25")
        // Aquí llegan en el array args: args[0] = "25"
        // ---------------------------------------------------------
        if (args.length > 0) {
            String dato = args[0].trim();
            try {
                int numero = Integer.parseInt(dato);
                System.out.println("Argumento recibido: " + numero);   // -> padre: getInputStream()
                System.exit(0);                                         // -> padre: waitFor() devuelve 0
            } catch (NumberFormatException e) {
                System.err.println("'" + dato + "' no es un entero");  // -> padre: getErrorStream()
                System.exit(2);                                         // -> padre: waitFor() devuelve 2
            }
        }

        // ---------------------------------------------------------
        // FORMA B: DATOS POR ENTRADA ESTÁNDAR (System.in)
        // System.in puede venir de:
        //   - el teclado (si lo ejecutas tú en Eclipse)
        //   - el padre, que escribe en p.getOutputStream()
        //   - un fichero, si el padre usa pb.redirectInput(fichero)
        // El hijo lee igual en los tres casos. ¡No cambia nada!
        // ---------------------------------------------------------
        Scanner sc = new Scanner(System.in);
        double suma = 0;
        int lineasLeidas = 0;

        // hasNextLine() devuelve false cuando la entrada se ACABA (EOF).
        // Eso ocurre cuando el padre hace close() del stream o se acaba el fichero.
        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();

            if (linea.equals("*")) {        // marca de fin acordada con el padre
                break;
            }
            if (linea.isEmpty()) {          // ignoramos líneas vacías
                continue;
            }

            lineasLeidas++;
            try {
                suma += Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.err.println("La línea '" + linea + "' no es un número");
                System.exit(3);
            }
        }

        if (lineasLeidas == 0) {
            System.err.println("No ha llegado ningún dato");
            System.exit(4);
        }

        System.out.println("Números sumados: " + lineasLeidas);
        System.out.println("Suma: " + suma);
        System.exit(0);
    }
}