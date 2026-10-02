package Ejercicio1_Multiproceso3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	String linea;
    	
    	try(BufferedReader lector = new BufferedReader(new FileReader("datos.txt"))){
    		linea = lector.readLine();
    		
    	}catch(IOException e) {
    		
    			System.exit(-1);
    			return;
    		
    		
    	}
    	
    	if(linea==null || linea.trim().isEmpty()) {
    		System.exit(-1);
    	}
    	
    	
    	int numero;
    	try {
    		numero= Integer.parseInt(linea);
    	}catch(NumberFormatException e) {
    		System.exit(-2);
    		return;
    	}
    	
    	if(numero>0) {
    		System.exit(-3);
    	}else {
    		System.exit(0);
    	}
    	
    	
    	
    }
        
    
}