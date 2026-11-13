package Practica_MultiHilo3;   // paquete de la práctica

// Contador: hilo aparte que va contando los segundos que dura el rescate.
// Implementa Runnable para poder lanzarlo con un ExecutorService.
public class Contador implements Runnable {

    // Segundos que han pasado desde que empezó el rescate
    private int segundos = 0;

    // Indica si el contador tiene que seguir contando.
    // volatile: el Main lo cambia desde otro hilo, y así este hilo ve el cambio al momento.
    private volatile boolean activo = true;

    @Override
    public void run() {                      // lo que ejecuta el hilo al arrancar
        while (activo) {                     // mientras nadie lo pare...
            try {
                Thread.sleep(1000);          // espera 1 segundo
                segundos++;                  // y suma 1 al contador
            } catch (InterruptedException e) {
                break;                       // si interrumpen el hilo, sale del bucle
            }
        }
        // Al salir del while, el contador ha terminado
        System.out.println("Contador parado");
    }

    // Lo llama el Main cuando acaba el rescate: pone activo a false
    // y en la siguiente vuelta el while termina.
    public void parar() {
        activo = false;
    }

    // Devuelve los segundos contados, para el informe final
    public int getSegundos() {
        return segundos;
    }
}