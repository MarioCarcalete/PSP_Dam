package Adivinar_Numero_HI;

/**
 * ÁRBITRO: es el OBJETO COMPARTIDO por todos los jugadores (no es un hilo).
 * Guarda el número a adivinar, de quién es el turno y si el juego ha acabado.
 * Todos los jugadores reciben LA MISMA instancia, por eso ven el mismo turno.
 */
public class Arbitro {

    private int numJugadores;     // cuántos jugadores hay (para saber cuándo volver al 1)
    private int turno;            // id del jugador al que le toca jugar ahora
    private int numeroAdivinar;   // número secreto entre 1 y 10
    private boolean fin;          // true cuando alguien acierta

    /**
     * Constructor: solo recibe el número de jugadores.
     * El resto se inicializa aquí dentro.
     */
    public Arbitro(int numJ) {
        this.numJugadores = numJ;
        this.turno = 1;                                       // empieza jugando el Jugador1
        // Math.random() -> [0, 1) ; *10 -> [0, 10) ; (int) -> 0..9 ; +1 -> 1..10
        this.numeroAdivinar = 1 + (int) (10 * Math.random());
        this.fin = false;                                     // el juego aún no ha acabado
        System.out.println("NÚMERO A ADIVINAR: " + numeroAdivinar);
    }

    /**
     * Devuelve de quién es el turno.
     * synchronized para que los jugadores siempre lean el valor ACTUALIZADO
     * (si no, un hilo podría quedarse viendo un turno antiguo y no jugar nunca).
     */
    public synchronized int getTurno() {
        return turno;
    }

    /**
     * Indica si el juego ha terminado.
     * Devuelve el ATRIBUTO fin (no true a fuego).
     * synchronized por el mismo motivo de visibilidad que getTurno().
     */
    public synchronized boolean isFin() {
        return fin;
    }

    /**
     * Comprueba la jugada de un jugador y calcula el siguiente turno.
     * synchronized: mientras un jugador está dentro de este método, ningún
     * otro hilo puede entrar en NINGÚN método synchronized de este árbitro
     * (el objeto queda "bloqueado" con su monitor/candado).
     *
     * @param idJugador el jugador que juega
     * @param numero    el número que dice
     */
    public synchronized void jugada(int idJugador, int numero) {
        // Protección extra: si el juego ya acabó, no se acepta ninguna jugada más.
        if (fin) {
            return;
        }

        System.out.println("Jugador" + idJugador + " dice: " + numero);

        if (numero == numeroAdivinar) {
            // ACIERTA: se acaba el juego. No se cambia el turno.
            fin = true;
            System.out.println("Jugador " + idJugador + " gana, adivinó el número!!!");
        } else {
            // FALLA: pasa el turno al siguiente.
            // Si era el último jugador, vuelve al 1; si no, suma 1.
            if (turno == numJugadores) {
                turno = 1;
            } else {
                turno++;
            }
            // (Equivalente en una línea: turno = turno % numJugadores + 1;)
            System.out.println("\tLe toca a Jug" + turno);
        }
    }
}