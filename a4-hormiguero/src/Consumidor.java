public class Consumidor implements Runnable {
	private Productor prod;
	
	Consumidor(Productor p){ prod = p; }

	@Override
	public void run() {
		while(true) {
			try {
				int value = prod.getValor();
				if(value % 2 == 0) {
					System.out.println("Recuperado valor par (" + value + ")");					
				} else {
					System.out.println("Recuperado valor impar (" + value + ")");
				}
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
