package ejercicios;


import static
	es.urjc.etsii.code.
	concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;



public class Ejercicio17 {
	
	private static int NUM_FILOSOFOS = 5;
	private static SimpleSemaphore []tenedor = new SimpleSemaphore [5];
	private static SimpleSemaphore total;
	
	
	public static void filosofo(int numFilosofo) {
		while (true) {
			printlnI("Pensar");
			// Obtener tenedores
			total.acquire();
			tenedor[numFilosofo].acquire();
			tenedor[(numFilosofo+1)%NUM_FILOSOFOS].acquire();
			 
			printlnI("Comer");
			// Liberar tenedores
			tenedor[numFilosofo].release();
			tenedor[(numFilosofo+1)%NUM_FILOSOFOS].release();
			total.release();
			}
		}
		
	

	public static void main(String args[]) {
		
		total = new SimpleSemaphore(NUM_FILOSOFOS-1);
		for(int i=0;i<NUM_FILOSOFOS;i++) {
			tenedor[i]= new SimpleSemaphore(1);
		}
		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			createThread("filosofo", i);
		}

		startThreadsAndWait();
	}
	
	
	
	
	
	

}
