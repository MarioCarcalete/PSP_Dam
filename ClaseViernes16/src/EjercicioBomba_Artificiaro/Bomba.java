package EjercicioBomba_Artificiaro;

public class Bomba extends Thread {
	
	private static final RANDOM = new Random();
	
	private Thread hiloArtificiero;
	
	private boolean soyBomba;
	
	public Bomba()

	
	
	
	
	public void desactivar() {
		boolean desactivada =false;
		try {
			int tiempo=(int) RANDOM.nextLong(95000,10501);
			
				Thread.sleep(tiempo);
			}catch(InteruptedExceptio e) {
				System.out.println(e);
		}
	}
	
	
	public void run() {
		boolean bandera = false;
		if(bandera) {
			cuantaAtras();
		}else {
			System.out.println();
		}
	}
}
