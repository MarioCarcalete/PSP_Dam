package Tic_y_Tact;

public class Tac extends Thread {
    private int tiempo;

    public Tac(int t) {
        this.tiempo = t;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("TAC");
                this.sleep(tiempo * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}   