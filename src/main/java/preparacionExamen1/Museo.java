package preparacionExamen1;
import static
es.urjc.etsii.code.
 concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;


public class Museo {
	private static SimpleSemaphore s1;
	 
	
	
	public static void visitante(int pid) {
		while(true) {
			s1.acquire();
			println(pid+": hola");
			println(pid+": que bonito");
			println(pid+": alucinante");
			s1.release();
			println(pid+": paseo");
		}
	}
	
	public static void main(String [] args) {
		s1 = new SimpleSemaphore(1);
		for (int i = 0; i < 3; i++) {
			createThread("visitante", i);
		}
		//createThreads(3, "visitante");
		startThreadsAndWait();
	}
	
	
}
