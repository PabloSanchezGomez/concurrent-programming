package ejercicios;


import static
	es.urjc.etsii.code.
	 concurrency.SimpleConcurrent.*;



public class Ejercicio11 {
	
	
	static volatile	boolean continuarP;
	static volatile	boolean continuarC;
	static volatile	boolean continuarS;
	static volatile int n;
	
	
	public static void cliente(){
		while(!continuarC);
		n = (int) (Math.random()*600 + 1);
		printlnI("1.Soy cliente: " + n);
		continuarC = false;
		continuarP = true;
		while(!continuarC);
		printlnI("1.Soy cliente: " + n);
	}
	
	public static void proxy(){
		while(!continuarP);
		n++;
		continuarP = false;
		continuarS = true;
		while(!continuarP);
		continuarP = false;
		continuarC = true;

	}
	public static void servidor() {
		while(!continuarS);
		n++;
		continuarS = false;
		continuarP = true;
	}
	
	public static void main(String[] args) {
		continuarC = true;
		continuarP = false;
		continuarS = false;
		
		
		createThread("cliente");
		createThread("proxy");
		createThread("servidor");
		
		startThreadsAndWait();
	}

}