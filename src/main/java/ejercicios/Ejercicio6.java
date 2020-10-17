package ejercicios;

import static
	es.urjc.etsii.code.
	concurrency.SimpleConcurrent.*;

public class Ejercicio6 {
	
	
	 static volatile int n;
	
	
	
	public static void persona(){
		
		while(true){
			enterMutex();
			n++;
			printlnI("hola, somos: " + n);
			exitMutex();
			
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
