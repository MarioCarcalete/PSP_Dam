package Relevos3;

public class main {
    public static void main(String[] args) {
        Relevo juan = new Relevo("Juan", "España");
        Relevo pepe = new Relevo("Pepe", "España");
        Relevo pepa = new Relevo("Pepa", "España");

        System.out.println("¡Empieza la carrera!");
        try {
            juan.start();
            juan.join();    // Pepe no sale hasta que Juan acaba
            pepe.start();
            pepe.join();    // Pepa no sale hasta que Pepe acaba
            pepa.start();
            pepa.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("España ha terminado el relevo");
    }
}