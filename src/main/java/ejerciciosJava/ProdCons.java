package ejerciciosJava;

public class ProdCons {
	private volatile boolean producido = false;
	private volatile double producto;
	
	public void productor() {
		producto = Math.random();
		producido = true;
	}
	public void consumidor() {
		while(!producido);
		System.out.println(producto);
	}
	
	public void exec() {
		new Thread(()->productor()).start();
		new Thread(()->consumidor()).start();
	}
	public static void main(String[] args) {
		new ProdCons() .exec();
	}

}
