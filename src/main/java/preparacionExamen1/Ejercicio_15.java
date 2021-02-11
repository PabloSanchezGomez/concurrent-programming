package preparacionExamen1;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;


public class Ejercicio_15 {
	
	private static int n= 0;
	private static SimpleSemaphore s1;
	private static SimpleSemaphore s2;
	
	
	
	
	public static void a() {
		while(true) {
			print("A");
			sincronizar();
		}
	}
	public static void b() {
		while(true) {
			print("B");
			sincronizar();
		}
	}
	public static void c() {
		while(true) {
			print("C");
			sincronizar();
		}
	}
	public static void d() {
		while(true) {
			print("D");
			sincronizar();
		}
	}
	
	public static void sincronizar() {
		s1.acquire();
		n++;
		if(n < 4) {
			s1.release();
			s2.acquire();
		}
		else {
			print("-");
			n = 0;
			s2.release(3);
			s1.release();
		}
		
	}
	
	
	
	public static void main(String [] args) {
		s1 = new SimpleSemaphore(1);
		s2 = new SimpleSemaphore(0);
		createThread("a");
		createThread("b");
		createThread("c");
		createThread("d");
		startThreadsAndWait();
	}
}
