package Tic_y_Tact;

public class Escritor extends Thread {
	private String palabra;
	private int tiempo;
	
	Escritor(String palabra,int tiempo){
		super();
		this.palabra=palabra;
		this.tiempo=tiempo;
	}
	
	public void Escritor() {
		try {
			for(int i =0;i<10;i++) {
				System.out.println(this.palabra);
				
				Thread.sleep(tiempo * 1000);
			}
		}catch(Exception e) {
			e.printStackTrace();;
		}
	}
	public void run() {
		this.Escritor();
	}

}
