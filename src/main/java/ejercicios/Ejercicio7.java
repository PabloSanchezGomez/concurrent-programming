package ejercicios;

import static
	es.urjc.etsii.code.
	concurrency.SimpleConcurrent.*;

public class Ejercicio7 {
	
	
	 static volatile int n;
	
	
	
	public static void persona(){
		
		boolean r;
		
		while(true){
			enterMutex();
			if(n == 0) {
				r = true;
			}
			else {
				r = false;
			}
			n++;
			printlnI("hola, somos: " + n);
			exitMutex();
			if(r){
				printlnI("Tengo regalo");
			}
			else {
				printlnI("No tengo regalo");
			}
			
			printlnI("qué bonito");
			printlnI("alucinante!");
			
			enterMutex();
			n--;
			printlnI("adios, somos: " + n);
			exitMutex();
			
			printlnI("paseo");
		}
		
	}
	public static void main(String[] args) {
		n = 0;
		createThreads(3,"persona");
		startThreadsAndWait();
	}
}
