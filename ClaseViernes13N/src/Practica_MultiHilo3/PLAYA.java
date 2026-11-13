package Practica_MultiHilo3;   // paquete donde está la clase

import java.util.concurrent.Semaphore;   // importamos la clase Semaphore

public class PLAYA {

    // Semáforo con 2 permisos: solo 2 barcas pueden estar en la playa a la vez.
    // Es private para que nadie lo toque desde fuera; solo se usa con entrar() y salir().
    private Semaphore semaforo = new Semaphore(2);

    // Náufragos que hay en la isla: un número aleatorio entre 800 y 1000.
    // Math.random() da un número entre 0 y 0.999...; por 201 da entre 0 y 200,999...
    // El (int) quita los decimales (0 a 200), y sumando 800 queda entre 800 y 1000.
    private int naufragos = 800 + (int)(Math.random() * 201);

    // La barca pide permiso para entrar en la playa.
    // Si ya hay 2 dentro, el hilo se queda bloqueado aquí hasta que salga una.
    // throws InterruptedException: acquire() puede interrumpirse mientras espera,
    // y dejamos que la excepción la gestione quien llame (la Barca).
    public void entrar() throws InterruptedException {
        semaforo.acquire();   // resta 1 permiso (o espera si no quedan)
    }

    // La barca sale de la playa y devuelve el permiso.
    // Si había otra barca esperando en acquire(), ahora puede entrar.
    public void salir() {
        semaforo.release();   // suma 1 permiso
    }

    // La barca recoge náufragos: tantos como plazas tiene, o los que queden si son menos.
    // synchronized: como hay 2 barcas dentro a la vez, sin esto las dos podrían
    // leer y restar "naufragos" al mismo tiempo y la cuenta saldría mal.
    public synchronized int recoger(int plazas) {
        // Coge el menor de los dos: si quedan 10 y caben 15, coge 10.
        int cogidos = Math.min(plazas, naufragos);
        // Resta de la isla los náufragos que se ha llevado.
        naufragos -= cogidos;
        // Devuelve cuántos ha subido, para que la Barca los sume a su total.
        return cogidos;
    }
    
    
    public synchronized boolean quedanNaufragos() {
    	if (naufragos!=0) {  // condicion de que si no es 0 devuelva true de que aun quedan
    		return true;
    	}
    	
    	return false;
    }
    public  int getNaufragos() {
    	return naufragos;
    }
}