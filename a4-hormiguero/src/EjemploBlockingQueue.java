import java.util.ArrayList;

public class EjemploBlockingQueue {
	final static int nConsumidores = 5;

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Thread> hilos = new ArrayList<Thread>();		
		Productor p = new Productor();
		Thread hiloProductor = new Thread(p);
		hilos.add(hiloProductor);
		for(int i = 0; i < nConsumidores; i++) {			
			Consumidor consumidor = new Consumidor(p);
			Thread hiloCons = new Thread(consumidor);			
			hilos.add(hiloCons);
		}	
		
		for(int i = 0; i < nConsumidores + 1; i++) {
			hilos.get(i).start();
		}
		
		for(int i = 0; i < nConsumidores + 1; i++) {
			hilos.get(i).join();
		}
	}

}
