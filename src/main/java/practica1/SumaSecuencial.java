package practica1;
import java.util.Arrays;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;

import static
es.urjc.etsii.code.
 concurrency.SimpleConcurrent.*;


public class SumaSecuencial {
	private static final int N_PROCESOS = 5;
	private static final int N_DATOS = 17;
	private static int nDatoActual = 0;
	private static int nDatosRestantes = N_DATOS;
	
	private static SimpleSemaphore[] semProc = new SimpleSemaphore[N_PROCESOS];
	private static SimpleSemaphore semDatos;
	private static int[] datos = new int[N_DATOS];
	private static int[] resultados;
	private static int nivel = 1;
	private static int procesosF = 0;
	private static int suma(int a, int b) {
		sleepRandom(1000);
		return a + b;
	}
	
	private static void inicializaDatos() {
		resultados = new int[Math.round(N_DATOS/2)];
		for (int i = 0; i < N_DATOS; i++) {
			datos[i] = (int) (Math.random() * 11);
		}
		println("Los datos a sumar son: "+Arrays.toString(datos));
	}
	
	private static void inicializaSemaforos() {
		for(int i=0;i<N_PROCESOS;i++) {
			semProc[i] = new SimpleSemaphore(1);
		}
		semDatos = new SimpleSemaphore(1);
	}
	
	private static void inicializaThreads() {
		for(int i=0;i<N_PROCESOS;i++) {
			createThread("sumaDatos", i);
		}
		startThreadsAndWait();
	}
	
	private static String printArray() {
		String resultado="[";
		for(int i=0;i<nDatosRestantes;i++) {
			resultado+=datos[i];
			if(i < nDatosRestantes -1) {
				resultado+=",";
			}
		}
		resultado+="]";
		return resultado;
	}
	
	public static void sumaDatos(int idProc) {
		semDatos.acquire();
		int dato = nDatoActual;
		nDatoActual+=2;
		if(nDatoActual > nDatosRestantes) {
			if(nDatosRestantes == 1) {
				semDatos.release();
				return;
			}
			for(int i=0;i<N_PROCESOS;i++) {
				semProc[i].acquire();
			}
			if(datos.length%2 == 1) {
				int valor = datos.length-1;
				println("Proceso "+idProc+": Se inicia la suma de datos ["+0+"]"+ "=" + resultados[0] + " y datos ["+ valor+"]="+ datos[datos.length-1]+".");
				resultados[0] = resultados[0]+datos[datos.length-1];
				println("Proceso "+idProc+": Se guarda la suma en resultados["+0+"]="+ resultados[0]+".");
			}
			nDatosRestantes = Math.round(nDatosRestantes/2);
			nDatoActual = 0;
			procesosF = 0;
			datos = resultados;
			println("Proceso "+idProc+": Se actualiza el valor de los datos a "+ printArray() +".");
			println("Proceso "+idProc+": Finalizado el nivel " + nivel);
			nivel++;
			println("----------------------------------------------------------");
			for(int i=0;i<N_PROCESOS;i++) {
				semProc[i].release();
			}
			semDatos.release();
		}else{
			
			semDatos.release();
			semProc[idProc].acquire();
			println("Proceso "+idProc+": Se inicia la suma de datos ["+dato+"] " + "=" + datos[dato] + " y datos ["+(dato+1)+"]="+ datos[(dato+1)]+".");
			resultados[dato/2] = suma(datos[dato], datos[dato+1]);
			println("Proceso "+idProc+": Se guarda la suma en resultados["+(dato/2)+"]="+ resultados[(dato/2)]+".");
			semProc[idProc].release();
			if(nDatoActual > nDatosRestantes) {
				procesosF++;
				println("Esperando a los demás procesos. Han terminado " + procesosF + " procesos.");
			}
		}

		sumaDatos(idProc);

	}
	
	public static void main(String[] args) {
		inicializaDatos();
		inicializaSemaforos();
		inicializaThreads();
		println("El resultado es: " + datos[0]);
	}
	

}
