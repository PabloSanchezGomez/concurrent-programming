package ejercicios;


import static
	es.urjc.etsii.code.
	 concurrency.SimpleConcurrent.*;



public class Ejercicio12 {
	
	
	static volatile	boolean continuarP;
	static volatile	boolean continuarC;
	static volatile	boolean continuarS;
	static volatile int n;
	
	
	public static void cliente(){
		for(int i = 0; i<10;i++) {
			while(!continuarC);
			n = (int) (Math.random()*600 + 1);
			printlnI("1.Soy cliente: " + n);
			continuarC = false;
			continuarP = true;
			while(!continuarC);
			printlnI("2.Soy cliente: " + n);
			}
	}
	
	public static void proxy(){
		while(true) {
			while(!continuarP);
			n++;
			continuarP = false;
			continuarS = true;
			while(!continuarP);
			continuarP = false;
			continuarC = true;
		}

	}
	public static void servidor() {
		while(true){
			while(!continuarS);
			n++;
			continuarS = false;
			continuarP = true;
		}
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