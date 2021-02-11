package ejercicios;


import static
	es.urjc.etsii.code.
	 concurrency.SimpleConcurrent.*;



public class Ejercicio10 {
	
	
	static volatile	boolean continuar1;
	static volatile	boolean continuar2;
	static volatile	boolean continuar3;
	static volatile	boolean continuar4;
	static volatile int n;
	
	
	public static void p1(){
		while(!continuar1);
		printlnI("A");
		printlnI("B");
		continuar3 = true;

	}
	
	public static void p2(){
		printlnI("C");
		continuar1 = true;
		while(!continuar2);
		printlnI("D");
		while(!continuar3 && !continuar4);
		printlnI("E");

	}
	public static void p3() {
		printlnI("F");
		continuar2 = true;
		printlnI("G");
		continuar4 = true;
		
	}
	
	public static void main(String[] args) {
		continuar1 = false;
		continuar2 = false;
		continuar3 = false;
		continuar4 = false;
		
		createThread("p1");
		createThread("p2");
		createThread("p3");
		
		startThreadsAndWait();
	}

}