package Ejercicio1HIlosBomba;

import java.net.BindException;

public class Ej1_impar extends Thread {
	
	
	
	public Ej1_impar() {
		super("HIlo-Impares");
	}
	@Override
	public void run() {
		for(int i=1;i<10;i++) {
			if(i%2!=0) {
				System.out.println(getName() + " -> " + i);
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					System.out.println(getName() + "Interrumpido ");
					return;
				}
			}
		}
		System.out.println(getName()  + " ha termiando ");
	}
	
	
	
}




