package ejerciciosJava;

public class ProdConsBuffer {
	
	private static final int NPROD = 3;
	private static final int NCONS = 5;
	private static Buffer buffer;
	
	
	public static void consumidor(){
		try {
			while(true){
				int data = buffer.sacar();
				System.out.println("consumido: " + data);
			}
			
		}catch(InterruptedException e){
			
		}
	}
	
	private static void productor(){
		try {
			for(int i = 0;i<20;i++) {
				System.out.println("producido: " + i);
				buffer.insertar(i);
			}
			
		}catch(InterruptedException e){
			
		}
	}
	public void exec() {
		buffer = new Buffer();
		for(int i = 0;i<NPROD;i++) {
			new Thread(()->productor(),"productor " + i).start();
		}
		for(int i = 0;i<NPROD;i++) {
			new Thread(()->consumidor(),"consumidor " + i).start();
		}
		
	}
	
	public static void main(String [] args) {
		
		new ProdConsBuffer().exec();
	
			
	}
	

}
