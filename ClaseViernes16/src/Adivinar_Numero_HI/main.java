package Adivinar_Numero_HI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * MAIN con ExecutorService: en vez de hacer new Thread / start() a mano,
 * creamos un POOL de hilos y le enviamos las tareas (los jugadores).
 */
public class main {

    public static void main(String[] args) {
        int numJugadores = 3;

        // Un ÚNICO árbitro compartido por todos los jugadores.
        Arbitro arbitro = new Arbitro(numJugadores);

        // Pool con TANTOS hilos como jugadores.
        // ¡IMPORTANTE! Si el pool tuviera menos hilos que jugadores (p. ej. 2),
        // los jugadores que esperan su turno ocuparían los hilos para siempre
        // (espera activa) y el jugador al que le toca nunca llegaría a ejecutarse:
        // el programa se quedaría colgado.
        ExecutorService pool = Executors.newFixedThreadPool(numJugadores);

        // Enviamos una tarea por jugador. execute() la pone en marcha en
        // uno de los hilos del pool (equivale al start() de antes).
        for (int i = 1; i <= numJugadores; i++) {
            pool.execute(new Jugador(i, arbitro));
        }

        // shutdown(): el pool NO acepta más tareas, pero deja terminar
        // las que ya están en marcha. Si no lo llamas, el programa no acaba
        // nunca porque los hilos del pool siguen vivos esperando trabajo.
        pool.shutdown();

        // awaitTermination(): el main espera (como un join) a que terminen
        // todas las tareas, con un tiempo máximo.
        try {
            if (pool.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Fin del juego.");
            } else {
                System.out.println("Tiempo agotado, se fuerza el cierre.");
                pool.shutdownNow();   // interrumpe las tareas que sigan en marcha
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }
}