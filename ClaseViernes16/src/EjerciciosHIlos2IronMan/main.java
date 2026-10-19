package EjerciciosHIlos2IronMan;

public class main {
    public static void main(String[] args) {

        // nombre, velCorrer (km/h), velBici (km/h), velNadar (m/h)
        Prueba pedro   = new Prueba("Pedro",   6, 20, 1000);
        Prueba juan    = new Prueba("Juan",    7, 18, 2000);
        Prueba vicente = new Prueba("Vicente", 6, 30, 1000);

        System.out.println("¡Empieza la carrera!");

        // start() seguidos → empiezan los tres a la vez
        pedro.start();
        juan.start();
        vicente.start();

        // el main espera a que terminen los tres
        try {
            pedro.join();
            juan.join();
            vicente.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Carrera terminada");
    }
}