package Hamburgueseria;

public class Lanzador {
	private final static int N_SILLA = 10;

	public static void main(String[] args) throws InterruptedException {
		Restaurante restaurante = new Restaurante(N_SILLA);
		
		while(true) {
			Cliente cliente = new Cliente(restaurante);
			Thread threadCliente = new Thread(cliente);
			threadCliente.start();
			Thread.sleep(1750);
		}
	}

}
