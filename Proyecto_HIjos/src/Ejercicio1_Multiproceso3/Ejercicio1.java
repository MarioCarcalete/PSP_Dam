package Ejercicio1_Multiproceso3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) throws IOException {
       System.out.println("Introduce un numero : ");
       Scanner teclado = new Scanner(System.in);
       int numero=teclado.nextInt(); 
       
       
       try(BufferedWriter ec = new BufferedWriter(new FileWriter("datos.txt"))){
    	   ec.write(numero);
    	   
    	   
    	   
       }catch(IOException e) {
    	   System.out.println("Error : " + e);
       }
       
       try(BufferedReader lc = new BufferedReader(new FileReader("datos.txt"))){
    	   String linea;
    	   while((linea=lc.readLine())!=null) {
    		   
    		   
    		   if (linea.trim().isBlank() && linea==null) {
    			   System.exit(-1);
    		   }
    		   int numeroF;
    		   try {
        		    numeroF = Integer.parseInt(linea);

    		   }catch(NumberFormatException e) {
    			   System.exit(-2);
    			   return;
    		   }
    		   if(numeroF>0) {
    			   System.exit(-3);
    		   }else {
    			   System.exit(0);
    		   }
    		   
    		   
    	   }
       }
    }
}