package EjercicioBomba_Artificiaro;   // Mismo paquete que Bomba, así no hace falta import

/**
 * Clase principal: crea los dos hilos (bomba y artificiero),
 * los enlaza entre sí y los arranca a la vez.
 *
 * El main también es un hilo (el hilo "main"). Al hacer start()
 * pasamos a tener 3 hilos ejecutándose: main, Bomba y Artificiero.
 */
public class main {

    public static void main(String[] args) {

        // -----------------------------------------------------------
        // 1. CREAR LOS HILOS
        // -----------------------------------------------------------
        // Usamos la misma clase Bomba para los dos; el segundo parámetro
        // decide el papel de cada uno:
        //   true  -> hace de bomba (cuenta atrás)
        //   false -> hace de artificiero (intenta desactivarla)
        // Crear el objeto NO arranca el hilo: de momento está en estado NEW.
        Bomba bomba = new Bomba("Bomba", true);
        Bomba artificiero = new Bomba("Artificiero", false);

        // -----------------------------------------------------------
        // 2. ENLAZAR LOS HILOS ENTRE SÍ
        // -----------------------------------------------------------
        // Cada hilo necesita una referencia al otro para poder
        // interrumpirlo con interrupt():
        //   - la bomba interrumpe al artificiero si explota antes
        //   - el artificiero interrumpe a la bomba si la desactiva a tiempo
        // Se hace después de crear los dos objetos porque, al crear
        // el primero, el segundo todavía no existía.
        bomba.setOtroHilo(artificiero);
        artificiero.setOtroHilo(bomba);

        // -----------------------------------------------------------
        // 3. ARRANCAR LOS HILOS
        // -----------------------------------------------------------
        // start() crea un hilo nuevo del sistema y ese hilo ejecuta run().
        // Los dos hilos pasan a RUNNABLE y se ejecutan EN PARALELO.
        // ¡Ojo! Si llamásemos a bomba.run() en vez de start(), la cuenta
        // atrás se ejecutaría en el hilo main y el artificiero no empezaría
        // hasta que la bomba terminase: no habría concurrencia.
        bomba.start();
        artificiero.start();

        // El orden de los mensajes en consola puede variar entre ejecuciones:
        // es el planificador del sistema operativo quien decide qué hilo
        // se ejecuta en cada momento.

        // Aquí el main termina, pero el programa NO acaba hasta que
        // terminan los dos hilos (no son hilos daemon).
        // Si quisiéramos esperar a que acaben para mostrar un mensaje final,
        // usaríamos join():
        //
        //   try {
        //       bomba.join();        // main espera a que termine la bomba
        //       artificiero.join();  // y luego al artificiero
        //   } catch (InterruptedException e) {
        //       e.printStackTrace();
        //   }
        //   System.out.println("Fin del programa");
    }
}