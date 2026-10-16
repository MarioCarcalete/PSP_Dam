package Ejercicio1HIlosBomba;
// Declara el paquete al que pertenece la clase.
// El nombre del directorio debe coincidir: Ejercicio1HIlosBomba/

import java.net.BindException;
// IMPORTA UNA EXCEPCIÓN DE RED (no se usa en el código, es un error).
// Lo correcto sería: import java.lang.InterruptedException; (aunque no hace falta porque java.lang se importa por defecto)

public class Ejercicio1 extends Thread {
// Declara la clase Ejercicio1 como subclase de Thread.
// Al heredar de Thread, ya tiene el método run() que sobreescribimos.

    public Ejercicio1() {
        super("HIlo-Pares");
        // Llama al constructor de Thread con un nombre para el hilo.
        // Este nombre se puede recuperar con getName().
        // (Ojo: tiene una errata, debería ser "Hilo-Pares")
    }

    @Override
    public void run() {
        // Este método se ejecuta cuando se llama a start().
        // Es el "cuerpo" del hilo.

        for (int i = 1; i < 10; i++) {
            // Bucle que recorre del 1 al 9 (i < 10, no incluye el 10).

            if (i % 2 == 0) {
                // Comprueba si i es par (resto de dividir entre 2 es 0).
                // Solo imprime los números pares: 2, 4, 6, 8.

                System.out.println(getName() + " -> " + i);
                // Imprime el nombre del hilo + el número par.
                // Ejemplo: "HIlo-Pares -> 2"

                try {
                    Thread.sleep(100);
                    // Pausa el hilo actual 100 milisegundos.
                    // Lanza InterruptedException si otro hilo lo interrumpe.
                } catch (InterruptedException e) {
                    // Captura la excepción si el hilo fue interrumpido.

                    System.out.println(getName() + "Interrumpido ");
                    // Imprime que el hilo ha sido interrumpido.
                    // (Falta un espacio antes de "Interrumpido")

                    return;
                    // Sale del método run(), terminando el hilo.
                }
            }
        }

        System.out.println(getName() + " ha termiando ");
        // Imprime que el hilo ha terminado.
        // (Ojo: errata, debería ser "terminado")
    }
}   