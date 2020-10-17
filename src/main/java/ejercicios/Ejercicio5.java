package ejercicios;

import static
	es.urjc.etsii.code.
	concurrency.SimpleConcurrent.*;

public class Ejercicio5 {
	public static void persona(){
		enterMutex();
		printlnI("hola!");
		printlnI("qué bonito");
		printlnI("alucinante!");
		printlnI("adios");
		exitMutex();
		printlnI("paseo");
		
	}
	public static void main(String[] args) {
		createThreads(3,"persona");
		startThreadsAndWait();
	}
}
