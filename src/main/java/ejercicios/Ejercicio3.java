package ejercicios;


import static
	es.urjc.etsii.code.
	 concurrency.SimpleConcurrent.*;


public class Ejercicio3 {
	
	static volatile	boolean continuarC;
	static volatile	boolean continuarS;
	static volatile int n;
	
	
	public static void servidor(){
			n = (int) (Math.random()*6 + 1);
			System.out.println(n);
			continuarC = true;
			while(!continuarS);
			String nf =String.valueOf(n);
			println(nf);
		}
		
	
	public static void cliente(){
			while(!continuarC);
				n++;
				continuarS = true;
	}
	
	public static void main(String[] args) {
		continuarC = false;	
		continuarS = false;
		
		
		createThread("servidor");
		createThread("cliente");
		
		startThreadsAndWait();
	}

}
