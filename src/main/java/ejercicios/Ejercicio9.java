
package ejercicios;

import static
es.urjc.etsii.code.
 concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejercicio9 {
	private static int counter = 0;
	private static int counter2 = 0;
	private static int counter3 = 0;
	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;
	private static volatile int[] fichero = new int[N_FRAGMENTOS];
	private static SimpleSemaphore s1;
	private static SimpleSemaphore s2;
	
	
	
	@SuppressWarnings("unused")
	private static int descargaDatos(int numFragmento) {
		sleepRandom(1000);
		return numFragmento * 2;
	}

	private static void mostrarFichero() {
			println("--------------------------------------------------");
			print("File = [");
			for (int i = 0; i < N_FRAGMENTOS; i++) {
			print(fichero[i] + ",");
			}
			println("]");
	}
	 
	
	public static void downloader() {
		while(counter3<10) {
			s1.acquire();
			if(counter>=N_FRAGMENTOS) {
				
				if(counter == 9) {
					counter2++;
					s1.release();
					s2.acquire();
				}
				else {
					counter = 0;
					counter2 = 0;
					counter3++;
					s2.release(9);
					println("Iteracion numero "+ counter3);
					s1.release();
				}
				
			}
			else {
				int n = counter;
				counter++;
				s1.release();
				println("Descargando fragmento " + n);
				int downloadData;
				downloadData = descargaDatos(n);
				println("Escribiendo fragmento " + n);
				fichero[n] = downloadData;
				
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		s1 = new SimpleSemaphore(1);
		s2 = new SimpleSemaphore(1);
		createThreads(N_HILOS, "downloader");
		startThreadsAndWait();
		mostrarFichero();
	}
}

