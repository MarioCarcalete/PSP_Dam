package Practica_MultiHilo3;   // paquete de la práctica

// Barca: cada barca es un hilo que va y viene rescatando náufragos.
// Implementa Runnable para poder lanzarla con un ExecutorService.
public class Barca implements Runnable {

    private String nombre;         // nombre de la barca ("Balsa 1", "Balsa 2"...)
    private PLAYA playa;           // la playa COMPARTIDA por todas las barcas
    private int rescatados = 0;    // náufragos que ha rescatado esta barca

    // Constructor: recibe el nombre y la playa.
    // Todas las barcas reciben el MISMO objeto playa, así comparten el semáforo y los náufragos.
    public Barca(String nombre, PLAYA playa) {
        this.nombre = nombre;
        this.playa = playa;
    }

    @Override
    public void run() {                               // lo que hace cada barca
        while (playa.quedanNaufragos()) {             // repite mientras queden náufragos en la isla
            try {
                playa.entrar();                       // pide sitio en la playa (acquire); si hay 2, espera aquí
                try {
                    // Sube a bordo hasta 15 náufragos (o los que queden si hay menos)
                    int cogidos = playa.recoger(15);
                    rescatados += cogidos;            // los suma a su total

                    // Mensajes del informe, como en el enunciado
                    System.out.println("La balsa actual es " + nombre + " y rescatamos a " + cogidos);
                    System.out.println("Quedan " + playa.getNaufragos() + " náufragos");

                    // Tiempo de carga en la playa: entre 2000 y 3000 ms
                    // (mínimo + random * (máximo - mínimo + 1))
                    Thread.sleep(2000 + (int)(Math.random() * 1001));
                } finally {
                    playa.salir();                    // sale de la playa (release); se ejecuta SIEMPRE
                }

                // Viaje al barco en alta mar y vuelta: entre 1800 y 2000 ms.
                // Se hace FUERA de la playa, así deja el hueco libre para otra barca.
                Thread.sleep(1800 + (int)(Math.random() * 201));

            } catch (InterruptedException e) {
                break;                                // si interrumpen el hilo, deja de rescatar
            }
        }
        // Ya no quedan náufragos: la barca termina y dice cuántos ha rescatado
        System.out.println("Ya no quedan náufragos y la " + nombre
                + " está volviendo a su puesto. Ha rescatado " + rescatados + " náufragos en total");
    }

    // Devuelve el nombre, para el informe del Main
    public String getNombre() {
        return nombre;
    }

    // Devuelve cuántos ha rescatado, para el informe del Main
    public int getRescatados() {
        return rescatados;
    }
}