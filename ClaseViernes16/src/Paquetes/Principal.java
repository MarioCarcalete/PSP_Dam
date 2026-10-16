package Paquetes;

public class Principal {
	public static void main(String[] args) {
		Raton fivel = new Raton("Fivel",5);
		Raton pipe = new Raton("Pipe",5);

		Raton jota = new Raton("JOta",5);

		Raton micky = new Raton("Mickey",5);
		
		new Thread(fivel).start();
		
		Thread p = new Thread(pipe);
		
		
		p.start();
		
		try {
			p.join();
		}catch(InterruptedException e) {
			
		}
		
		
		new Thread(jota).start();
		new Thread(micky).start();
		
	}

}


// join para ver quien empieza qn se mete lgo para terminar y los otros 

