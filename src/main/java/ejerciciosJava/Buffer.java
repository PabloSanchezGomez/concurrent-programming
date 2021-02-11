package ejerciciosJava;
import java.util.concurrent.Semaphore;
public class Buffer {
	
	private static final int SIZE = 10;
	private int[] datos = new int[SIZE];
	private int posicionInsertar = 0;
	private int posicionSacar = 0;
	private Semaphore insertar = new Semaphore(1);
	private Semaphore sacar = new Semaphore(1);
	private Semaphore productos = new Semaphore(0);
	private Semaphore huecos = new Semaphore(SIZE);
	
	
	public void insertar(int dato) throws InterruptedException {
		huecos.acquire();
		insertar.acquire();
		datos[posicionInsertar] = dato;
		posicionInsertar = (posicionInsertar+1)%SIZE;
		insertar.release();
		productos.release();
	}
	public int sacar() throws InterruptedException {
		productos.acquire();
		sacar.acquire();
		int dato = datos[posicionSacar];
		posicionSacar = (posicionSacar+1)%SIZE;
		sacar.release();
		huecos.release();
		return dato;
	}

}
