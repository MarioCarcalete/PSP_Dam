package Tic_y_Tact;

public class Tic extends Thread {
	
	private int tiempo;
	public Tic( int t) {
		this.tiempo=t;
	}
	
	@Override
	public void run() {
		while (true){
		try {
			System.out.println("Tic");
			this.sleep(tiempo * 1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
