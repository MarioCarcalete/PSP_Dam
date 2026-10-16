package Paquetes;

public class Raton  extends Thread{
	
	private String nombre;
	private int tiempo;
	public Raton(String n,int t) {
		super();
		this.nombre=n;
		this.tiempo=t;
		
	}
	
	public void comer() {
		try {
			System.out.printf("El raton " + this.nombre + " ha comenzado a comer%n ",nombre);
			this.sleep(tiempo * 1000);
			System.out.printf("El raton " + this.nombre + " ha terminado de alimentarse %N",nombre);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	@Override
	
	public void run() {
		this.comer();
	}
	
	

}
