package ejercicios;


import static
	es.urjc.etsii.code.
	 concurrency.SimpleConcurrent.*;


public class Ejercicio4 {
	
	static volatile	boolean continuarC;
	static volatile	boolean continuarS;
	static volatile int n;
	
	
	public static void servidor(){
		while(true) {
			n = (int) (Math.random()*6 + 1);
			System.out.println(n);
			continuarC = true;
			while(!continuarS);
			String nf =String.valueOf(n);
			println(nf);
			continuarS = false;
		}
	}
	
	public static void cliente(){
		while(true){
			while(!continuarC);
				n++;
				continuarC =false;
				continuarS = true;
	}
	}
	public static void main(String[] args) {
		continuarC = false;	
		continuarS = false;
		
		
		createThread("servidor");
		createThread("cliente");
		
		startThreadsAndWait();
	}

}