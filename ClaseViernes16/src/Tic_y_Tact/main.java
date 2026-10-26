package Tic_y_Tact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
	
	public static void main(String[] args) throws InterruptedException {
		Escritor tic = new Escritor("Tic",1);
		Escritor tac = new Escritor("Tac",1);
		Escritor Toc = new Escritor("Toc",1);
		Escritor Tuc = new Escritor("Tuc",1);

		
		ExecutorService miEjecutor = Executors.newFixedThreadPool(2);
		
		
		
		//tic.start();
		//Thread.sleep(500);
		//tac.start;
		//toc.start;
		
		miEjecutor.submit(tic);
		miEjecutor.submit(tac);
		miEjecutor.submit(Toc);
		miEjecutor.submit(Tuc);
		
		System.out.println("Hemos acabado ");

		
	}

}
// solo puedes heredar uno y interface hacer mas cantidad 


