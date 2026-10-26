package EjercicioBomba_Artificiaro;   // Paquete: debe coincidir con el nombre de la carpeta en Eclipse

import java.util.Random;               // Clase para generar números aleatorios

/**
 * Ejercicio Bomba y Artificiero (PSP - Hilos).
 *
 * La MISMA clase sirve para crear dos hilos distintos:
 *   - Si soyBomba = true  -> el hilo hace de BOMBA: cuenta atrás de 10 segundos.
 *   - Si soyBomba = false -> el hilo hace de ARTIFICIERO: tarda un tiempo aleatorio
 *                            en desactivar la bomba.
 *
 * Los dos hilos se comunican con interrupt():
 *   - Si el artificiero termina ANTES -> interrumpe a la bomba -> bomba desactivada.
 *   - Si la bomba llega a 0 ANTES     -> explota e interrumpe al artificiero.
 *
 * Extendemos de Thread, así que cada objeto Bomba ES un hilo
 * y lo que hace se escribe en el método run().
 */
public class Bomba extends Thread {

    // ---------------------------------------------------------------
    // ATRIBUTOS
    // ---------------------------------------------------------------

    // static -> un único Random compartido por todos los objetos de la clase.
    // final  -> la referencia no cambia nunca (es una constante).
    // Se escribe en MAYÚSCULAS por convención de constantes.
    private static final Random RANDOM = new Random();

    // Duración de la cuenta atrás de la bomba (en segundos).
    private static final int SEGUNDOS = 10;

    // Referencia al OTRO hilo:
    //   - en la bomba apunta al artificiero
    //   - en el artificiero apunta a la bomba
    // Hace falta para poder llamar a otroHilo.interrupt().
    private Thread otroHilo;

    // Indica qué papel hace este hilo: true = bomba, false = artificiero.
    // Es final porque se decide en el constructor y ya no cambia.
    private final boolean soyBomba;

    // ---------------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------------

    /**
     * @param nombre   nombre del hilo (sale en los mensajes con getName())
     * @param soyBomba true si este hilo es la bomba, false si es el artificiero
     */
    public Bomba(String nombre, boolean soyBomba) {
        super(nombre);              // Llama al constructor de Thread para poner nombre al hilo
        this.soyBomba = soyBomba;   // Guardamos el papel de este hilo
    }

    // ---------------------------------------------------------------
    // SETTER
    // ---------------------------------------------------------------

    /**
     * Enlaza este hilo con el contrario.
     * Se usa un setter (y no el constructor) porque cuando creamos la bomba
     * el artificiero todavía no existe, y viceversa: primero se crean los dos
     * objetos y después se enlazan entre sí en el main.
     */
    public void setOtroHilo(Thread otroHilo) {
        this.otroHilo = otroHilo;
    }

    // ---------------------------------------------------------------
    // COMPORTAMIENTO DE LA BOMBA
    // ---------------------------------------------------------------

    /**
     * Cuenta atrás de 10 a 1, esperando 1 segundo en cada número.
     * Si nadie la interrumpe -> explota.
     * Si el artificiero la interrumpe mientras duerme -> salta la
     * InterruptedException y la bomba queda desactivada.
     */
    private void cuentaAtras() {
        try {
            for (int i = SEGUNDOS; i > 0; i--) {
                System.out.println(getName() + ": " + i + "...");
                // sleep() duerme el hilo 1000 ms (1 segundo).
                // Si otro hilo hace interrupt() mientras duerme,
                // sleep() lanza InterruptedException y saltamos al catch.
                Thread.sleep(1000);
            }

            // Si llegamos aquí es que el bucle ha terminado sin interrupciones:
            // nadie la ha desactivado a tiempo.
            System.out.println("¡BOOM! La bomba ha explotado.");

            // Avisamos al artificiero (que sigue durmiendo) de que ya no hace falta seguir.
            otroHilo.interrupt();

        } catch (InterruptedException e) {
            // Nos han interrumpido durante un sleep() -> el artificiero cortó el cable.
            System.out.println("Bomba desactivada a tiempo. ¡Salvados!");
        }
    }

    // ---------------------------------------------------------------
    // COMPORTAMIENTO DEL ARTIFICIERO
    // ---------------------------------------------------------------

    /**
     * El artificiero tarda un tiempo aleatorio entre 9,5 y 10,5 segundos.
     * Como la bomba tarda 10 s, a veces llega a tiempo y a veces no.
     */
    private void desactivar() {
        try {
            // nextInt(origen, límite) -> número entre 9500 (incluido) y 10501 (excluido),
            // es decir, de 9500 a 10500 ms. El origen SIEMPRE debe ser menor que el límite.
            // (Este método existe desde Java 17.)
            int tiempo = RANDOM.nextInt(9500, 10501);
            System.out.println(getName() + " tardará " + tiempo + " ms en desactivarla");

            // El artificiero "trabaja" (duerme) ese tiempo.
            Thread.sleep(tiempo);

            // Si se despierta sin que le interrumpan, ha terminado a tiempo:
            // interrumpe a la bomba para desactivarla.
            otroHilo.interrupt();

        } catch (InterruptedException e) {
            // Le han interrumpido mientras trabajaba -> la bomba explotó antes.
            System.out.println(getName() + ": no me ha dado tiempo...");
        }
    }

    // ---------------------------------------------------------------
    // MÉTODO run(): lo que ejecuta el hilo al hacer start()
    // ---------------------------------------------------------------

    /**
     * IMPORTANTE: nunca se llama a run() directamente, se llama a start().
     * start() crea un hilo nuevo y ese hilo ejecuta run().
     * Si llamas a run() a mano, se ejecuta en el hilo main, sin concurrencia.
     */
    @Override
    public void run() {
        if (soyBomba) {
            cuentaAtras();   // Papel de bomba
        } else {
            desactivar();    // Papel de artificiero
        }
    }
}