package Relevos3;   // Paquete (carpeta) donde está la clase

// Relevo hereda de Thread: cada objeto Relevo es un hilo
// que se puede ejecutar a la vez que otros
public class Relevo extends Thread {

    // ===== ATRIBUTOS =====
    private String nombre;   // Nombre del corredor (Juan, Pepe...)
    private String pais;     // Equipo al que pertenece (España...)

    // ===== CONSTRUCTOR =====
    // Se ejecuta al hacer new Relevo("Juan", "España")
    // y guarda los datos en los atributos del objeto
    public Relevo(String nombre, String pais) {
        this.nombre = nombre;   // this.nombre = atributo; nombre = parámetro recibido
        this.pais = pais;
    }

    // ===== MÉTODO QUE SIMULA LA CARRERA DE 100 METROS =====
    public void correr() {
        int total = 0;   // Acumula los milisegundos que tarda en total

        // try/catch obligatorio porque Thread.sleep() puede lanzar InterruptedException
        // (si otro hilo interrumpe a este mientras duerme)
        try {
            // Aviso de que empieza: %s se sustituye por pais y nombre, %n = salto de línea
            System.out.printf("%s: %s comienza su relevo%n", pais, nombre);

            // Los 100 m se dividen en 10 tramos de 10 m
            for (int i = 0; i < 10; i++) {

                // Tiempo aleatorio del tramo entre 950 y 1050 ms:
                // Math.random() da un número de 0.0 a 0.999...
                // * 101 → de 0 a 100.99...
                // (int) quita los decimales → de 0 a 100
                // + 950 → de 950 a 1050
                int tramo = (int) (Math.random() * 101) + 950;

                // El hilo se "duerme" ese tiempo: simula que está corriendo el tramo
                Thread.sleep(tramo);

                // Suma el tiempo del tramo al total
                total += tramo;
            }

            // Aviso de que termina con el tiempo total.
            // total está en ms; al dividir entre 1000.0 (con decimal) pasa a segundos
            // con decimales. %.2f lo muestra con 2 decimales
            System.out.printf("%s: %s ha acabado su relevo - Ha tardado %.2f s%n",
                    pais, nombre, total / 1000.0);

        } catch (InterruptedException e) {
            e.printStackTrace();   // Si interrumpen el sleep, muestra el error
        }
    }

    // ===== MÉTODO run() =====
    // @Override: sobrescribe el run() de Thread.
    // Es lo que ejecuta el hilo cuando en el main se hace corredor.start()
    @Override
    public void run() {
        correr();
    }
}