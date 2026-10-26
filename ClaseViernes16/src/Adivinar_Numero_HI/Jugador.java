package Adivinar_Numero_HI;

/**
 * JUGADOR: cada jugador es un HILO (extends Thread).
 * Todos comparten el mismo objeto Arbitro.
 */
public class Jugador extends Thread {

    private int id;            // identificador del jugador (1, 2, 3...)
    private Arbitro arbitro;   // referencia al árbitro COMPARTIDO

    /**
     * @param id      identificador del jugador
     * @param arbitro el árbitro que comparten todos los jugadores
     */
    public Jugador(int id, Arbitro arbitro) {
        this.id = id;
        this.arbitro = arbitro;
    }

    /**
     * Lo que hace el hilo al hacer start().
     * Repite mientras el juego no se haya acabado:
     *   - si es su turno -> genera un número del 1 al 10 y hace la jugada
     *   - si no es su turno -> no hace nada y vuelve a comprobar
     * Esto se llama ESPERA ACTIVA (busy waiting): el hilo pregunta una y otra
     * vez "¿me toca?". Funciona, pero gasta CPU mientras espera.
     */
    @Override
    public void run() {
        while (!arbitro.isFin()) {
            if (arbitro.getTurno() == id) {
                int numero = 1 + (int) (10 * Math.random());   // número entre 1 y 10
                arbitro.jugada(id, numero);
            }
        }
        // Al salir del bucle el juego ha terminado y el hilo acaba.
    }
}