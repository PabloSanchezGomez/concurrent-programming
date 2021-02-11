package ejercicios;
import static
es.urjc.etsii.code.
concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;


public class Ejercicio19 {
	private static int NUM_LECTORES = 5;
	private static int NUM_ESCRITORES = 3;
	private static int escritoresE = 0; //escritores espera
	private static int lectoresE = 0; // lectores espera
	private static int escritoresD = 0; // escritores dentro
	private static int lectoresD = 0; // lectores dentro
	
	private static SimpleSemaphore control = new SimpleSemaphore(1);
	private static SimpleSemaphore escritura = new SimpleSemaphore(0);
	private static SimpleSemaphore lectura = new SimpleSemaphore(0);
	private static SimpleSemaphore eE = new SimpleSemaphore(1); // exclusion entre escritores

	public static void inicioLectura(){
		control.acquire();
		if(escritoresE > 0 && escritoresD > 0) {
			lectoresE++;
			control.release();
			lectura.acquire();
			control.acquire();
			lectoresE--;
			lectoresD++;
			control.release();
		}
		else {
			lectoresD++;
			control.release();
		}
	}
	
	
	public static void finLectura(){
		control.acquire();
		lectoresD--;
		if(lectoresD == 0) {
			escritura.release(escritoresE);
		}
		control.release();
		
	}
	
	
	public static void inicioEscritura(){
		control.acquire();
		if(lectoresD > 0) {
			escritoresE++;
			control.release();
			escritura.acquire();
			control.acquire();
			escritoresE--;
			escritoresD++;
			control.release();
		}
		else{
			escritoresD++;
			control.release();
		}

	
	}
	public static void finEscritura(){
		control.acquire();
		escritoresD--;
		if(escritoresD == 0 && escritoresE == 0 ) {
			lectura.release(lectoresE);
		}
		control.release();
	}
	
	
	
	
	
	
	
	public static void lector(){
		while(true){
			inicioLectura();
			println("Leer datos");
			finLectura();
			println("Procesar datos");
		}
	}
	public static void escritor() {
		while (true) {
			println("Generar datos");
			inicioEscritura();
			println("Escribir datos");
			finEscritura();
		}
	}
	public static void main(String[] args) {
		createThreads(NUM_LECTORES, "lector");
		createThreads(NUM_ESCRITORES, "escritor");
		startThreadsAndWait();
	}

}
