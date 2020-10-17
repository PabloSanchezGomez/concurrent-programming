package ejercicios;


import static
	es.urjc.etsii.code.
	 concurrency.SimpleConcurrent.*;



public class Ejercicio1 {
	
	
	static volatile	boolean continuar;
	static volatile int n;
	
	
	public static void productor(){
		n = (int) (Math.random()*6 + 1);
		continuar = true;
	}
	
	public static void consumidor(){
		while(!continuar);
			System.out.println(n);

	}
	
	public static void main(String[] args) {
		continuar = false;
		
		
		createThread("productor");
		createThread("consumidor");
		
		startThreadsAndWait();
	}

}
