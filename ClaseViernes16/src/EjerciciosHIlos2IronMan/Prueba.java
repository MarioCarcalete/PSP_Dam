package EjerciciosHIlos2IronMan;

public class Prueba extends Thread {
    private String nombre;
    private double velCorrer;   // km/h
    private double velBici;     // km/h
    private double velNadar;    // m/h

    public Prueba(String nombre, double velCorrer, double velBici, double velNadar) {
        this.nombre = nombre;
        this.velCorrer = velCorrer;
        this.velBici = velBici;
        this.velNadar = velNadar;
    }

    // Devuelve las horas que se tarda: distancia / velocidad
    public double tiempo(double distancia, double velocidad) {
        return distancia / velocidad;
    }

    // Simula una prueba: avisa al empezar, espera lo que dura y avisa al acabar
    public void prueba(String nombrePrueba, double distancia, double velocidad) {
        double horas = tiempo(distancia, velocidad);
        try {
            System.out.printf("%s empieza a %s%n", nombre, nombrePrueba);
            Thread.sleep((long) (horas * 1000));   // 1 hora = 1000 ms
            System.out.printf("%s termina de %s (%.1f horas)%n", nombre, nombrePrueba, horas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        prueba("nadar", 4000, velNadar);    // 4.000 m
        prueba("bici", 180, velBici);       // 180 km
        prueba("correr", 42, velCorrer);    // 42 km
        System.out.printf(">>> %s ha terminado el Iron Man%n", nombre);
    }
}