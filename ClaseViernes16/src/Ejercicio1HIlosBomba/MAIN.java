package Ejercicio1HIlosBomba;
// Paquete donde están todas las clases del ejercicio.

public class MAIN {
// Clase principal. (Convención: se escribe "Main", no "MAIN")

    public static void main(String[] args) {
        // Punto de entrada del programa. Aquí se crean y lanzan los hilos.

        Ejercicio1 par = new Ejercicio1();
        // Crea una instancia del hilo de pares (nombre interno: "HIlo-Pares").
        // Todavía NO está corriendo.

        Ej1_impar impar = new Ej1_impar();
        // Crea una instancia del hilo de impares.
        // Todavía NO está corriendo.

        impar.start();
        // Lanza el hilo de impares. Java crea un nuevo hilo de ejecución
        // y llama automáticamente a su método run().

        par.start();
        // Lanza el hilo de pares. Igual que arriba.
        // A partir de aquí, ambos hilos corren EN PARALELO.

        try {
            impar.join();
            // El hilo principal (main) SE BLOQUEA y espera a que
            // el hilo "impar" termine su run() antes de continuar.

            par.join();
            // El hilo principal espera a que el hilo "par" también termine.
            // (En la práctica, como join() se llama en secuencia,
            //  espera a que ambos hayan terminado.)

        } catch (InterruptedException e) {
            // Si algún otro hilo llama a interrupt() sobre el hilo principal,
            // join() lanza InterruptedException y se captura aquí.
            e.printStackTrace();
            // Imprime el stack trace de la excepción (pila de llamadas).
        }

        System.out.println("Ha termiando ");
        // Imprime que el programa ha terminado.
        // (Errata: debería ser "Ha terminado")
        // Esta línea NO se ejecuta hasta que ambos hilos hayan terminado,
        // gracias a los join().
    }
}   