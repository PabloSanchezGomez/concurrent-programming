package ejercicios;


import static
	es.urjc.etsii.code.
	concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;



public class Ejercicio13B {
	

	private static final int NUM_TRENES = 5;
	private static SimpleSemaphore tramoA;
	private static SimpleSemaphore tramoB;
	private static SimpleSemaphore tramoC;
	
	
	
	public static void tren(int numTren) {
		sleepRandom(500);
		tramoA.acquire();
		recorrerTramoA(numTren);
		sleepRandom(500);
		tramoB.acquire();
		tramoA.release();
		recorrerTramoB(numTren);
		sleepRandom(500);
		tramoC.acquire();
		tramoB.release();
		recorrerTramoC(numTren);
		tramoC.release();
		
	}
	
	
	
	private static void recorrerTramoA(int numTren) {
		printlnI("Entra TA T" + numTren);
		sleepRandom(500);
		printlnI("Sale TA T" + numTren);
	}
	private static void recorrerTramoB(int numTren) {
		printlnI("Entra TB T" + numTren);
		sleepRandom(500);
		printlnI("Sale TB T" + numTren);
	}
	private static void recorrerTramoC(int numTren) {
		printlnI("Entra TC T" + numTren);
		sleepRandom(500);
		printlnI("Sale TC T" + numTren);
	}

		

	


	
	
	
	
	public static void main(String args[]) {
		
		tramoA = new SimpleSemaphore(1);
		tramoB = new SimpleSemaphore(1);
		tramoC = new SimpleSemaphore(1);
		
		
		for (int i = 0; i < NUM_TRENES; i++) {
			createThread("tren", i);
		}
		startThreadsAndWait();
	}
	
	
	
	
	
	

}