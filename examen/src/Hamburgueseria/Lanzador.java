package Hamburgueseria;

public class Lanzador {
	private final static int N_SILLA = 10;

	public static void main(String[] args) throws InterruptedException {
		Restaurante restaurante = new Restaurante(N_SILLA);
		Cocina cocina = new Cocina(restaurante);

		int clientes = 0;

		while(clientes < 10) {
			Cliente cliente = new Cliente(restaurante, cocina);
			Thread threadCliente = new Thread(cliente);
			threadCliente.start();
			Thread.sleep(1750);
			clientes++;
		}
	}
}
