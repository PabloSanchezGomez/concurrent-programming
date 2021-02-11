package preparacionExamen1;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejercicio14 {
	private static final int N = 20;
	private static int counter = 0;
	private static SimpleSemaphore s1;
	private static SimpleSemaphore s2;
	public static void escribir() {
		print("A");
		s2.acquire();
		counter++;
		if(counter == N) {
			s2.release();
			s1.release(19);
		}
		else {
			s2.release();
			s1.acquire();
		}
		print("B");
		
	}
	public static void main(String [] args) {
		s2 = new SimpleSemaphore(1);
		s1 = new SimpleSemaphore(0);
		createThreads(20,"escribir");
		startThreadsAndWait();
		
	}
}
