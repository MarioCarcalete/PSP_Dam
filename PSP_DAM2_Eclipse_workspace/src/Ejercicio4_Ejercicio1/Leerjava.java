package Ejercicio4_Ejercicio1;



import java.util.Scanner;



public class Leerjava {
	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("EScribe un nombre");
		
		
		String nombre = teclado.nextLine();
		int contadorV =0;
		
		if(nombre.length()<3) {
			System.exit(-1);
		}
			for(int i=0;i<nombre.length();i++) {
				
			
				char c = Character.toLowerCase(nombre.charAt(i));
			    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			        // es vocal
			    	contadorV +=1;
			    }
			}	
			
			
		
		
		System.out.println("Vocales : " + contadorV);
		  System.exit(0);
		
		
		
	}

}
