package Tic_y_Tact;

public class main {
	
	public static void main(String[] args) throws InterruptedException {
		Tic tic = new Tic(1);
		Tac tac = new Tac(1);
		
		
		
		tic.start();
		Thread.sleep(500);
		tac.start();
	}

}
// solo puedes heredar uno y interface hacer mas cantidad 