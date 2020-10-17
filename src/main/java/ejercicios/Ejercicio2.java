package ejercicios;


import static
	es.urjc.etsii.code.
	 concurrency.SimpleConcurrent.*;



public class Ejercicio2 {
	
	
	static volatile	boolean continuarC;
	static volatile boolean continuarP;
	static volatile int n;
	
	
	public static void productor(){
		while(true) {
			while(!continuarP);
			n = (int) (Math.random()*6 + 1);
			continuarP = false;
			continuarC = true;
		}
		
	}
	
	public static void consumidor(){
		while(true) {
			while(!continuarC);
				System.out.println(n);
				continuarC = false;
				continuarP = true;
		}
	}
	
	public static void main(String[] args) {
		continuarC = false;
		continuarP = true;
		
		
		createThread("productor");
		createThread("consumidor");
		
		startThreadsAndWait();
	}

}
